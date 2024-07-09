package com.carenation.car.domain.category.repository

import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.category.entity.CarCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarCategoryRepository : JpaRepository<CarCategoryEntity, Long> {
    fun findByCategoryEntityId(categoryEntityId : Long): List<CarCategoryEntity>
    fun deleteByCarEntity(carEntity: CarEntity)
}