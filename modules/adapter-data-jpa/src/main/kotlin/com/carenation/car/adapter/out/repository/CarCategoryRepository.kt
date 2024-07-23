package com.carenation.car.adapter.out.repository

import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface CarCategoryRepository : JpaRepository<CarCategoryEntity, Long> {

    // Query를 사용하여 자동차의 id로 CarCategory 엔티티를 삭제
    @Modifying
    @Query("DELETE FROM CarCategoryEntity c WHERE c.carEntity.id = :carId")
    fun deleteByCarId(@Param("carId") carId: Long)

}