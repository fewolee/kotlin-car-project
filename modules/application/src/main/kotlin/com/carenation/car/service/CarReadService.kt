package com.carenation.car.service

import com.carenation.car.application.domain.CarModel
import com.carenation.car.dto.CarInfoListInDto
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import com.carenation.car.port.out.CarReadOutPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * CarReadUseCase 인터페이스의 구현체
 */
@Service
class CarReadService(
    private val carReadOutPort: CarReadOutPort,
) : CarReadUseCase {
    // 자동차 ID로 단건 조회
    @Transactional(readOnly = true)
    override fun getById(carId: Long): CarModel = carReadOutPort.getById(carId)

    // 모든 자동차 정보 조회
    @Transactional(readOnly = true)
    override fun getAll(): List<CarModel> = carReadOutPort.getAll()

    // 카테고리 이름으로 자동차 정보 조회
    @Transactional(readOnly = true)
    override fun getByCategoryName(categoryName: String): List<CarModel> = carReadOutPort.getByCategoryName(categoryName)

    // 모델명, 제조사, 생상년도로 동적으로 자동차 정보를 조회
    @Transactional(readOnly = true)
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarModel> = carReadOutPort.getDynamicQuery(req)

    // carId로 Car Entity 존재 여부 확인
    @Transactional(readOnly = true)
    override fun existsByCarId(carId: Long): Boolean = carReadOutPort.existsByCarId(carId)

    // category로 Category Entity 존재 여부 확인
    @Transactional(readOnly = true)
    override fun existsByCategory(category: String): Boolean = carReadOutPort.existsByCategory(category)
}
