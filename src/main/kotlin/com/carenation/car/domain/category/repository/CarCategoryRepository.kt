package com.carenation.car.domain.category.repository

import com.carenation.car.domain.category.entity.CarCategory
import com.carenation.car.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CarCategoryRepository : JpaRepository<CarCategory, Long> {
    fun findByCategoryId(categoryId : Long): List<CarCategory>
}