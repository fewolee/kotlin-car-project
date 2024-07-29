package com.carenation.car.service

import com.carenation.car.dto.CarCreateInDto
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.out.CarCreateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarCreateUseCase 인터페이스의 구현체
 */
@Service
class CarCreateService(
    private val carCreateRepository: CarCreateRepository,
) : CarCreateUseCase {
    // 자동차 생성
    @Transactional
    override fun create(carCreateInDto: CarCreateInDto) {
        // Car 엔티티 등록 후 Car ID 반환
        val carId = carCreateRepository.saveCar(carCreateInDto)
        // CarCategory 등록
        if (carId != null) {
            carCreateRepository.saveCarCategory(carCreateInDto.categoryNames, carId)
        }
    }
}
