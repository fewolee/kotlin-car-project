package com.carenation.car.port.`in`.usecase

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.application.domain.CarInfoListInDto

/***
input port
 ***/
interface CarReadUseCase {

    // 자동차의 id로 자동차 엔티티를 조회해 CarInfoDto로 반환
    fun getById(carId: Long): CarInfoDto

    // 자동차의 모든 엔티티를 조회해 CarInfoDto 리스트로 반환
    fun getAll(): List<CarInfoDto>

    // 카테고리 이름으로 자동차 엔티티를 조회해 CarInfDto 리스트로 반환
    fun getByCategoryName(categoryName: String): List<CarInfoDto>

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarInfoDto 리스트로 반환
    fun getDynamicQuery(req: CarInfoListInDto): List<CarInfoDto>

}