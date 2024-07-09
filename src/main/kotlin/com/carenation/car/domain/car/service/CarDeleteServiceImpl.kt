package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.repository.CarRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarDeleteServiceImpl(
    private val carRepository: CarRepository
): CarDeleteService{

    @Transactional
    override fun delete(carId: Long) {

        carRepository.deleteById(carId)

    }
}