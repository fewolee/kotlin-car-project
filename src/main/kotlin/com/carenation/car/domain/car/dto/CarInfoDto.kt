package com.carenation.car.domain.car.dto

// 자동차의 정보를 보여주기 위한 Dto
data class CarInfoDto (
    val modelName: String, // 모델명
    val manufacture: String, // 제조사
    val productionYear: Int, // 생산년도
    val rentAvailable: Boolean // 대여 가능 여부
)