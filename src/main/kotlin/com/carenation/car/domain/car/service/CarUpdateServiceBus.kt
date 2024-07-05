package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarDto

interface CarUpdateServiceBus {
    fun update(id : Long, updateCarDto: UpdateCarDto): UpdatedCarDto
}