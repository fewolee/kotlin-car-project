package com.carenation.car.service

import com.carenation.car.dto.CarCreateInDto
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.out.CarCreateOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarCreateUseCase 인터페이스의 구현체
 */
@Service
class CarCreateService(
    private val carCreateOutPort: CarCreateOutPort,
) : CarCreateUseCase {
    // 자동차 생성
    @Transactional
    override fun create(carCreateInDto: CarCreateInDto) {
        val carId = carCreateOutPort.saveCar(carCreateInDto) ?: throw IllegalArgumentException("자동차 ID가 없습니다")
        carCreateOutPort.saveCarCategory(carCreateInDto.categoryNames, carId)
    }
}
