package com.carenation.car.port

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse
import com.carenation.car.port.`in`.usecase.CarUseCase
import com.carenation.car.port.out.CarOutport

class CarService(
    private val carOutport: CarOutport
) : CarUseCase {
    override fun create(createCarRequest: CreateCarRequest): CreatedCarResponse {
        TODO("Not yet implemented")
    }

}