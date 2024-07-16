package com.carenation.car.application.service

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse
import com.carenation.car.port.`in`.usecase.CarUseCase
import com.carenation.car.port.out.CarOutport
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarService (
    private val carOutport : CarOutport
): CarUseCase{

    @Transactional
    override fun create(req: CreateCarRequest): CreatedCarResponse {
       return carOutport.create(req)
    }
}
