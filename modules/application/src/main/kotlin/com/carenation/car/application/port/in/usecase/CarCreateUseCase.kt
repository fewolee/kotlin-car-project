package com.carenation.car.application.port.`in`.usecase

import com.carenation.car.application.dto.CarCreateInDto

/**
 * input port
 */
interface CarCreateUseCase {
    // 자동차 등록
    fun create(carCreateInDto: CarCreateInDto)
}
