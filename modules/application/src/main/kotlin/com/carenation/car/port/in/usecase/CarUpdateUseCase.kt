package com.carenation.car.port.`in`.usecase

import com.carenation.car.dto.CarUpdateInDto

/**
 * input port
 */
interface CarUpdateUseCase {
    // 자동차 정보 수정
    fun update(carUpdateInDto: CarUpdateInDto)
}
