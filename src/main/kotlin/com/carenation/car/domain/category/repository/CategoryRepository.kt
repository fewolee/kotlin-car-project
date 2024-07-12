package com.carenation.car.domain.category.repository

import com.carenation.car.domain.category.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long>{

    // 카테고리 이름으로 카테고리 엔티티 조회
    fun findByCategoryName(categoryName : String): CategoryEntity

}