package com.carenation.car.application.service

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.out.CarCreateOutPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CarCreateService (
    private val carCreateOutPort : CarCreateOutPort
): CarCreateUseCase{

    @Transactional
    override fun create(req: CreateCarRequest): CreatedCarResponse {
       return carCreateOutPort.create(req)
    }

}
