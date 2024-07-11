package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.repository.CarCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarDeleteServiceImpl(
    private val carRepository: CarRepository,
    private val carCategoryRepository: CarCategoryRepository
): CarDeleteService{

    @Transactional
    override fun delete(carId: Long) {

        carCategoryRepository.deleteByCarId(carId)
        carRepository.deleteById(carId)
    }
}