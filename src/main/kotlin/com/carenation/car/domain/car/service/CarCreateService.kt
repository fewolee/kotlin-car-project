package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.request.CreateCarRequest
import com.carenation.car.domain.car.dto.response.CreatedCarResponse

interface CarCreateService {
    // 자동차 생성
    fun create(registerDto: CreateCarRequest): CreatedCarResponse
}