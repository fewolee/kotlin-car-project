package com.carenation.car.port.`in`.usecase

import com.carenation.car.dto.CarCreateInDto

/**
 * input port
 */
interface CarCreateUseCase {
    // 자동차 등록
    fun create(carCreateInDto: CarCreateInDto)
}
