package com.carenation.car.port.out

import com.carenation.car.application.domain.CarDetailModel
import com.carenation.car.dto.CarUpdateInDto

/**
 * out port
 */
interface CarUpdateOutPort {
    // 자동차 정보 수정
    fun update(carUpdateInDto: CarUpdateInDto): CarDetailModel
}
