package com.carenation.car.port.`in`.usecase

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse

interface CarUseCase {
    fun create(createCarRequest: CreateCarRequest) : CreatedCarResponse
}