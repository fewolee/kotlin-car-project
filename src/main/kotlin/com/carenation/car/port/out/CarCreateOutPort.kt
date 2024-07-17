package com.carenation.car.port.out

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse

// 내부 영역이 외부 영역으로 사용하기 위한 통로
interface CarCreateOutPort {

    //자동차 등록
    fun create(createCarRequest: CreateCarRequest) : CreatedCarResponse


}