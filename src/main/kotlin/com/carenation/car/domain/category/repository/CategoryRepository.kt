package com.carenation.car.domain.category.repository

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>{

    fun findByCategoryName(categoryName : String): Category

}