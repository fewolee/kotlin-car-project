package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    fun findByRentAvailableTrue(): List<Car>
}
