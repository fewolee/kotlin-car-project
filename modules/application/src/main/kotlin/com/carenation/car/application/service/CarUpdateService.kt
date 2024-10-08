package com.carenation.car.application.service

import com.carenation.car.application.dto.CarUpdateInDto
import com.carenation.car.application.port.`in`.usecase.CarUpdateUseCase
import com.carenation.car.application.port.out.CarUpdateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarUpdateUseCase 인터페이스의 구현체
 */
@Service
class CarUpdateService(
    private val carUpdateRepository: CarUpdateRepository,
) : CarUpdateUseCase {
    // 자동차 정보 수정
    @Transactional
    override fun update(carUpdateInDto: CarUpdateInDto) {
        // Car Entity 정보 수정
        carUpdateRepository.updateCar(carUpdateInDto)
        // CarCategory Entity 정보 수정
        carUpdateRepository.updateCarCategory(carUpdateInDto.id, carUpdateInDto.categoryNames)
    }
}
