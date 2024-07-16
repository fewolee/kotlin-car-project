package com.carenation.car.port.out

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse

// 내부 영역이 외부 영역으로 사용하기 위한 통로
interface CarOutport {
    fun create(createCarRequest: CreateCarRequest) : CreatedCarResponse
}