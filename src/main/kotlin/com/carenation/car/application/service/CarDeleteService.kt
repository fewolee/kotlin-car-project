package com.carenation.car.application.service

import com.carenation.car.port.`in`.usecase.CarDeleteUseCase
import com.carenation.car.port.out.CarDeleteOutPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarDeleteService(
    private val carDeleteOutPort: CarDeleteOutPort
): CarDeleteUseCase {

    @Transactional
    override fun delete(carId: Long) {
        return carDeleteOutPort.delete(carId)
    }

}