package com.carenation.car.service

import com.carenation.car.port.`in`.usecase.CarDeleteUseCase
import com.carenation.car.port.out.CarDeleteOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarDeleteUseCase 인터페이스의 구현체
 */
@Service
class CarDeleteService(
    private val carDeleteOutPort: CarDeleteOutPort
): CarDeleteUseCase {

    // 자동차 삭제
    @Transactional
    override fun delete(carId: Long) {
        return carDeleteOutPort.delete(carId)
    }

}