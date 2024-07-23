package com.carenation.car.adapter.out

import com.carenation.car.adapter.out.mapper.CarOutMapper
import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.repository.CarCategoryRepository
import com.carenation.car.adapter.out.repository.CarRepository
import com.carenation.car.adapter.out.repository.CategoryRepository
import com.carenation.car.application.domain.CarDetailModel
import com.carenation.car.dto.CarUpdateInDto
import com.carenation.car.port.out.CarUpdateOutPort
import org.springframework.stereotype.Component

/**
 * CarUpdateOutPort의 구현체
 */
@Component
class CarUpdateAdapter(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carOutMapper: CarOutMapper,
) : CarUpdateOutPort {
    /**
     * 자동차 정보 수정
     * @param CarUpdateInDto
     * @return CarUpdateOutDto
     */
    override fun update(carUpdateInDto: CarUpdateInDto): CarDetailModel {
        // carEntity 수정
        val updatedCar = updateCarEntity(carUpdateInDto)

        // Mapper를 이용하여 updateCarEntity와 updateCarCategoryEntity 메서드를 이용해 UpdatedCarDto로 반환
        return carOutMapper.toCarDetailModel(updatedCar, updateCarCategoryEntity(carUpdateInDto, updatedCar))
    }

    // carEntity 업데이트
    private fun updateCarEntity(carUpdateInDto: CarUpdateInDto): CarEntity {
        // 자동차 id로 자동차 엔티티 조회
        val car =
            carRepository
                .findById(carUpdateInDto.id)
                .orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: ${carUpdateInDto.id}") }

        // 자동차 정보 수정
        car.updateInfo(
            modelName = carUpdateInDto.modelName,
            manufacture = carUpdateInDto.manufacture,
            productionYear = carUpdateInDto.productionYear,
            rentAvailable = carUpdateInDto.rentAvailable,
        )
        // CarRepository에 저장
        return carRepository.save(car)
    }

    // carCategoryEntity 업데이트
    private fun updateCarCategoryEntity(
        carUpdateInDto: CarUpdateInDto,
        updatedCar: CarEntity,
    ): List<String> {
        val categoryNames = mutableListOf<String>()
        // 자동차 id로 CarCategoryRepository에 원래 있던 엔티티 삭제
        carCategoryRepository.deleteByCarId(carUpdateInDto.id)

        // 수정할 UpdateCarDto로 CarCategory 엔티티 생성 및 저장
        carUpdateInDto.categoryNames.forEach { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = updatedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }

        // 수정한 카테고리 리스트 반환
        return categoryNames
    }
}
