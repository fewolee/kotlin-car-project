package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.response.UpdatedCarResponse
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.mapper.CarMapper
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.car.entity.CarCategoryEntity
import com.carenation.car.domain.car.repository.CarCategoryRepository
import com.carenation.car.domain.car.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CarUpdateServiceImpl(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carMapper : CarMapper
) : CarUpdateService {

    //자동차 정보 수정
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


}

