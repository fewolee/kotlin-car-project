package com.carenation.car.application.port.`in`.usecase

import com.carenation.car.application.dto.CarUpdateInDto

/**
 * input port
 */
interface CarUpdateUseCase {
    // 자동차 정보 수정
    fun update(carUpdateInDto: CarUpdateInDto)
}
