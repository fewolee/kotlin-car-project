package com.carenation.car.port.`in`.usecase

import com.carenation.car.application.domain.CarModel
import com.carenation.car.dto.CarInfoListInDto

/**
 * input port
 */
interface CarReadUseCase {
    // 자동차의 id로 자동차 엔티티를 조회해 CarModel 반환
    fun getById(carId: Long): CarModel

    // 자동차의 모든 엔티티를 조회해 CarModel 리스트로 반환
    fun getAll(): List<CarModel>

    // 카테고리 이름으로 자동차 엔티티를 조회해 CarModel 리스트로 반환
    fun getByCategoryName(categoryName: String): List<CarModel>

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarModel 리스트로 반환
    fun getDynamicQuery(req: CarInfoListInDto): List<CarModel>

    // carId로 CarEntity 존재 여부 확인
    fun existsByCarId(carId: Long): Boolean

    // category로 Category Entity 존재 여부 확인
    fun existsByCategory(category: String): Boolean
}
