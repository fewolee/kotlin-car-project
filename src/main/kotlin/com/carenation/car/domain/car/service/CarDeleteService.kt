package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.repository.CarRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarDeleteService(
    private val carRepository: CarRepository
): CarDeleteServiceBus{

    @Transactional
    override fun delete(carId: Long) {

        carRepository.deleteById(carId)

    }
}