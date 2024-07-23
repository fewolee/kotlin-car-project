package com.carenation.car.port.out

import com.carenation.car.application.domain.CarUpdateInDto
import com.carenation.car.application.domain.CarUpdateOutDto

/**
 * out port
 */
interface CarUpdateOutPort {
    // 자동차 정보 수정
    fun update(carUpdateInDto: CarUpdateInDto): CarUpdateOutDto
}