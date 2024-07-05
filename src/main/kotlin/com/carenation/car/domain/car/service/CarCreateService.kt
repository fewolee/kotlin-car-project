package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategoryEntity
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class CarCreateService (
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
): CarCreateServiceBus{
    // 자동차 등록
    override fun register(
        registerCarDto: RegisterCarDto
    ): RegisteredCarDto {
        var carEntity = CarEntity(
            modelName = registerCarDto.modelName,
            manufacture = registerCarDto.manufacture,
            productionYear = registerCarDto.productionYear,
            rentAvailable = registerCarDto.rentAvailable
        )

        val savedCar = carRepository.save(carEntity)
        val categoryNames = mutableListOf<String>()

        registerCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }


        return RegisteredCarDto(
            modelName = savedCar.modelName,
            manufacture = savedCar.manufacture,
            productionYear = savedCar.productionYear,
            rentAvailable = savedCar.rentAvailable,
            categoryNames = categoryNames
        )
    }
}