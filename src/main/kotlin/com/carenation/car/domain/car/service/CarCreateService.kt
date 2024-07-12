package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CreateCarRequest
import com.carenation.car.domain.car.dto.CreatedCarResponse

interface CarCreateService {
    fun register(registerDto: CreateCarRequest): CreatedCarResponse
}