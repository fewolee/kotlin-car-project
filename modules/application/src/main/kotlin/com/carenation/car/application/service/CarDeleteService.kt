package com.carenation.car.application.service

import com.carenation.car.application.port.`in`.usecase.CarDeleteUseCase
import com.carenation.car.application.port.out.CarDeleteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarDeleteUseCase 인터페이스의 구현체
 */
@Service
class CarDeleteService(
    private val carDeleteRepository: CarDeleteRepository,
) : CarDeleteUseCase {
    // 자동차 삭제
    @Transactional
    override fun delete(carId: Long) {
        // CarCategory 엔티티 먼저 삭제
        carDeleteRepository.deleteCarCategory(carId)
        // Car 엔티티 삭제
        carDeleteRepository.deleteCar(carId)
    }
}
