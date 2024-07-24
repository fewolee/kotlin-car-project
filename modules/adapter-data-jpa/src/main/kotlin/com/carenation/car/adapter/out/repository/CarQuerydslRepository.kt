package com.carenation.car.adapter.out.repository

import com.carenation.car.application.domain.CarModel
import com.carenation.car.dto.CarInfoListInDto

// Querydsl을 이용해 자동차 정보를 조회하는 인터페이스
interface CarQuerydslRepository {
    // 자동차의 id로 자동차 엔티티를 조회해 CarModel로 반환
    fun getById(carId: Long): CarModel

    // 자동차의 모든 엔티티를 조회해 List<CarModel> 로 반환
    fun getAll(): List<CarModel>

    // 카테고리 이름으로 자동차 엔티티를 조회해 List<CarModel>로 반환
    fun getByCategoryName(categoryName: String): List<CarModel>

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 List<CarModel>로 반환
    fun getDynamicQuery(req: CarInfoListInDto): List<CarModel>
}
