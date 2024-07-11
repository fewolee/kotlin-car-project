package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<CarEntity, Long> , CarQuerydslRepository {

    fun findByIdIn(ids : List<Long>) : List<CarEntity>

}