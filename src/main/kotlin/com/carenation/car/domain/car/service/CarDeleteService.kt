package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.repository.CarRepository

import com.carenation.car.domain.category.repository.CarCategoryRepository
import org.springframework.stereotype.Service


@Service
class CarDeleteService(
    private val carRepository: CarRepository
): CarDeleteServiceBus{

    override fun delete(carId: Long) : Unit {
        carRepository.deleteById(carId)
    }
}