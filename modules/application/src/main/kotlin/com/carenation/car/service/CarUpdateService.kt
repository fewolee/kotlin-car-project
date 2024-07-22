package com.carenation.car.service

import com.carenation.car.application.domain.CarUpdateInDto
import com.carenation.car.application.domain.CarUpdateOutDto
import com.carenation.car.port.`in`.usecase.CarUpdateUseCase
import com.carenation.car.port.out.CarUpdateOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarUpdateUseCase 인터페이스의 구현체
 */
@Service
class CarUpdateService (
    private val carUpdateOutPort: CarUpdateOutPort
): CarUpdateUseCase {


    // 자동차 정보 수정
    @Transactional
    override fun update(carUpdateInDto: CarUpdateInDto): CarUpdateOutDto {
        return carUpdateOutPort.update(carUpdateInDto)
    }

}