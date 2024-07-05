package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto

interface CarCreateServiceBus {
    fun register(registerDto: RegisterCarDto): RegisteredCarDto
}