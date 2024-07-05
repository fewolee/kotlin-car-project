package com.carenation.car.domain.category.repository

import com.carenation.car.domain.category.entity.CarCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CarCategoryRepository : JpaRepository<CarCategoryEntity, Long> {
    fun findByCategoryEntityId(categoryEntityId : Long): List<CarCategoryEntity>
}