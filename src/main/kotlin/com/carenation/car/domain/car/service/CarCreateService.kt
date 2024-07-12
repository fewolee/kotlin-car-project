package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.request.CreateCarRequest
import com.carenation.car.domain.car.dto.response.CreatedCarResponse

interface CarCreateService {
    fun register(registerDto: CreateCarRequest): CreatedCarResponse
}