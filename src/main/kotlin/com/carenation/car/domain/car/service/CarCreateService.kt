package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto

interface CarCreateService {
    fun register(registerDto: RegisterCarDto): RegisteredCarDto
}