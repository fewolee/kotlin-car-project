package com.carenation.car.adapter.out.persistence.repository

import com.carenation.car.adapter.`in`.dto.CarInfoDto
import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse
import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.persistence.mapper.CarMapper
import com.carenation.car.port.out.CarOutport
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class CarPersistAdapter(

    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carMapper : CarMapper
) : CarOutport{

    @Transactional
    override fun create(req: CreateCarRequest): CreatedCarResponse {
        //영속화 목적 entity로 변환
        var car = CarEntity(
            modelName = req.modelName,
            manufacture = req.manufacture,
            productionYear = req.productionYear,
            rentAvailable = req.rentAvailable
        )

        // 영속화
        val savedCar = carRepository.save(car)

        // 카테고리 영속화
        val categoryNames = mutableListOf<String>()
        req.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }


        // 생성한 Car 엔티티를 Mapper를 통해 CreatedCarDto로 반환
        return carMapper.toCreatedCarDto(car)


    }

    @Transactional
    override fun getById(carId: Long): CarInfoDto {
        return carRepository.getById(carId)
    }

    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carRepository.getAll()
    }

    @Transactional
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carRepository.getByCategoryName(categoryName)
    }

    @Transactional
    override fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto> {
        return carRepository.getDynamicQuery(req)
    }

    @Transactional
    override fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse {

        // carEntity 수정
        val updatedCar = updateCarEntity(updateCarDto)

        // Mapper를 이용하여 updateCarEntity와 updateCarCategoryEntity 메서드를 이용해 UpdatedCarDto로 반환
        return carMapper.toUpdatedCarDto(updatedCar,updateCarCategoryEntity(updateCarDto, updatedCar))

    }


    // carEntity 업데이트
    private fun updateCarEntity(updateCarDto: UpdateCarDto): CarEntity {

        // 자동차 id로 자동차 엔티티 조회
        val car = carRepository.findById(updateCarDto.id)
            .orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: ${updateCarDto.id}") }

        // 자동차 정보 수정
        car.updateInfo(modelName = updateCarDto.modelName,
            manufacture = updateCarDto.manufacture,
            productionYear = updateCarDto.productionYear,
            rentAvailable = updateCarDto.rentAvailable)
        // CarRepository에 저장
        return carRepository.save(car)
    }

    // carCategoryEntity 업데이트
    private fun updateCarCategoryEntity(updateCarDto: UpdateCarDto, updatedCar: CarEntity): List<String> {

        val categoryNames = mutableListOf<String>()
        // 자동차 id로 CarCategoryRepository에 원래 있던 엔티티 삭제
        carCategoryRepository.deleteByCarId(updateCarDto.id)

        // 수정할 UpdateCarDto로 CarCategory 엔티티 생성 및 저장
        updateCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = updatedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }
        // 수정한 카테고리 리스트 반환
        return categoryNames
    }



    // 자동차 삭제
    @Transactional
    override fun delete(carId: Long) {
        // CarCategoryRepository에서 자동차 id에 해당하는 엔티티 먼저 삭제
        carCategoryRepository.deleteByCarId(carId)
        // 이후에 CarRepository에서 자동차 엔티티 삭제
        carRepository.deleteById(carId)
    }
}