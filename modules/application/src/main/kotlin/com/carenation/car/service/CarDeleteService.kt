package com.carenation.car.service

import com.carenation.car.port.`in`.usecase.CarDeleteUseCase
import com.carenation.car.port.out.CarDeleteOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarDeleteService(
    private val carDeleteOutPort: CarDeleteOutPort
): CarDeleteUseCase {

    @Transactional
    override fun delete(carId: Long) {
        return carDeleteOutPort.delete(carId)
    }

}