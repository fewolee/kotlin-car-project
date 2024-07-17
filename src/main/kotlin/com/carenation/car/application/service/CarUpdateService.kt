package com.carenation.car.application.service

import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse
import com.carenation.car.port.`in`.usecase.CarUpdateUseCase
import com.carenation.car.port.out.CarUpdateOutPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarUpdateService (
    private val carUpdateOutPort: CarUpdateOutPort
): CarUpdateUseCase {


    @Transactional
    override fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse {
        return carUpdateOutPort.update(updateCarDto)
    }

}