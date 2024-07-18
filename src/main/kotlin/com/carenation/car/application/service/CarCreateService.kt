package com.carenation.car.application.service

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.application.domain.CarAllInfo
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.out.CarCreateOutPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CarCreateService (
    private val carCreateOutPort : CarCreateOutPort
): CarCreateUseCase{

    @Transactional
    override fun create(carAllInfo: CarAllInfo): CarCreateOutDto {
       return carCreateOutPort.create(carAllInfo)
    }

}
