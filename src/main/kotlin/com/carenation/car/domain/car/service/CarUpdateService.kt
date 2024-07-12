package com.carenation.car.domain.car.service


import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarResponse

interface CarUpdateService {
    fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse
}