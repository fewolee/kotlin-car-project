package com.carenation.car.port.`in`.usecase

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse

/***
input port
 ***/

interface CarCreateUseCase {

    // 자동차 등록
    fun create(createCarRequest: CreateCarRequest): CreatedCarResponse

}