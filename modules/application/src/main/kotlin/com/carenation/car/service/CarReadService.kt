package com.carenation.car.service

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.application.domain.CarInfoListInDto
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import com.carenation.car.port.out.CarReadOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarReadUseCase 인터페이스의 구현체
 */
@Service
class CarReadService (
    private val carReadOutPort: CarReadOutPort
): CarReadUseCase {

    // 자동차 ID로 단건 조회
    @Transactional
    override fun getById(carId: Long): CarInfoDto {
        return carReadOutPort.getById(carId)
    }

    // 모든 자동차 정보 조회
    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carReadOutPort.getAll()
    }

    // 카테고리 이름으로 자동차 정보 조회
    @Transactional
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carReadOutPort.getByCategoryName(categoryName)
    }

    // 모델명, 제조사, 생상년도로 동적으로 자동차 정보를 조회
    @Transactional
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarInfoDto> {
        return  carReadOutPort.getDynamicQuery(req)
    }
}