package com.carenation.car.application.domain

/**
 * 모델명, 제조사, 생산년도로 자동차를 동적으로 조회하기 위해 요청하는 Dto
 */
data class CarInfoListInDto(
    val modelName: String?, // 모델명

    val manufacture: String?, // 제조사

    val productionYear: Int? // 생산년도

)

