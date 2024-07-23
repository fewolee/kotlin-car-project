package com.carenation.car.port.out

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.application.domain.CarAllInfoDto

/**
 * out port
 */
interface CarCreateOutPort {
    //자동차 생성
    fun create(carAllInfoDto: CarAllInfoDto) : CarCreateOutDto
}