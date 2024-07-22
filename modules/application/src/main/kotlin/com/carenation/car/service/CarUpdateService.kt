package com.carenation.car.service

import com.carenation.car.application.domain.CarUpdateInDto
import com.carenation.car.application.domain.CarUpdateOutDto
import com.carenation.car.port.`in`.usecase.CarUpdateUseCase
import com.carenation.car.port.out.CarUpdateOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarUpdateService (
    private val carUpdateOutPort: CarUpdateOutPort
): CarUpdateUseCase {


    @Transactional
    override fun update(carUpdateInDto: CarUpdateInDto): CarUpdateOutDto {
        return carUpdateOutPort.update(carUpdateInDto)
    }

}