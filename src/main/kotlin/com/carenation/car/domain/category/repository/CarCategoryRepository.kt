package com.carenation.car.domain.category.repository

import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.category.entity.CarCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CarCategoryRepository : JpaRepository<CarCategoryEntity, Long> {

    @Modifying
    @Query("DELETE FROM CarCategoryEntity c WHERE c.carEntity.id = :carId")
    fun deleteByCarId(@Param("carId") carId: Long)

}