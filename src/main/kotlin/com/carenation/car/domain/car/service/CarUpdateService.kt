package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarDto
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategoryEntity
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CarUpdateService(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
) : CarUpdateServiceBus {

    @Transactional
    override fun update(id: Long, updateCarDto: UpdateCarDto): UpdatedCarDto {
        var carEntity = carRepository.findById(id).orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: $id") }
        carEntity.modelName = updateCarDto.modelName
        carEntity.manufacture = updateCarDto.manufacture
        carEntity.rentAvailable = updateCarDto.rentAvailable
        carEntity.productionYear = updateCarDto.productionYear

        var updatedCar = carRepository.save(carEntity)
        val categoryNames = mutableListOf<String>()

        carCategoryRepository.deleteByCarEntity(carEntity)

        updateCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = updatedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }

        return UpdatedCarDto(
            modelName = updatedCar.modelName,
            manufacture = updatedCar.manufacture,
            productionYear = updatedCar.productionYear,
            rentAvailable = updatedCar.rentAvailable,
            categoryNames = categoryNames
        )

    }

}

