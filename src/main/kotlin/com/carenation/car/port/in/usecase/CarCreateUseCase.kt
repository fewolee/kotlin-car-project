package com.carenation.car.port.`in`.usecase

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.application.domain.CarAllInfo

/***
input port
 ***/

interface CarCreateUseCase {

    // 자동차 등록
    fun create(carAllInfo: CarAllInfo): CarCreateOutDto

}