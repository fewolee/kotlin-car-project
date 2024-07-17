package com.carenation.car.port.`in`.usecase

import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse

/***
input port
 ***/
interface CarUpdateUseCase {


    // 자동차 정보 수정
    fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse

}