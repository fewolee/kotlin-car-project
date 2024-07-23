package com.carenation.car.port.out

import com.carenation.car.application.domain.CarDetailModel
import com.carenation.car.dto.CarCreateInDto

/**
 * out port
 */
interface CarCreateOutPort {
    // 자동차 생성
    fun create(carCreateInDto: CarCreateInDto): CarDetailModel
}
