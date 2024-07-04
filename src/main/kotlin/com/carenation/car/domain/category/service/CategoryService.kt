package com.carenation.car.domain.category.service

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carRepository: CarRepository
) {
    fun getCarsByCategoryName(categoryName: String): List<Car> {
        val category = categoryRepository.findByCategoryName(categoryName)
            ?: throw IllegalArgumentException("Category not found: $categoryName")

        val carCategories = carCategoryRepository.findByCategoryId(category.id!!)
        val carIds = carCategories.map { it.car.id!! }

        return carRepository.findByIdIn(carIds)
    }
}