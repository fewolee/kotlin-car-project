package com.carenation.car.domain.category.repository

import com.carenation.car.domain.category.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long>{

    fun findByCategoryName(categoryName : String): CategoryEntity

}