package com.carenation.car.service

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.application.domain.CarAllInfoDto
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.out.CarCreateOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarCreateService (
    private val carCreateOutPort : CarCreateOutPort
): CarCreateUseCase{


    @Transactional
    override fun create(carAllInfoDto: CarAllInfoDto): CarCreateOutDto {
       return carCreateOutPort.create(carAllInfoDto)
    }

}
