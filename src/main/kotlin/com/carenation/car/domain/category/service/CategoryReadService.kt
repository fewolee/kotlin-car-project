package com.carenation.car.domain.category.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class CategoryReadService(
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carRepository: CarRepository
) : CategoryReadServiceBus{
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {

        val category = categoryRepository.findByCategoryName(categoryName)
            ?: throw IllegalArgumentException("해당하는 카테고리 이름이 없습니다: $categoryName")

        val carCategories = carCategoryRepository.findByCategoryEntityId(category.id!!)
        val carIds = carCategories.map { it.carEntity.id!! }

        return carRepository.findByIdIn(carIds)
            .map { carEntity ->
                CarInfoDto(
                    modelName = carEntity.modelName,
                    manufacture = carEntity.manufacture,
                    productionYear = carEntity.productionYear,
                    rentAvailable = carEntity.rentAvailable
                )

    }


    }


}