package com.carenation.car.port.out

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.application.domain.CarAllInfo

/*
생성 관련 인터페이스
 */
interface CarCreateOutPort {

    //자동차 등록
    fun create(carAllInfo: CarAllInfo) : CarCreateOutDto


}