package com.carenation.car.domain

/**
 * 카테고리를 제외한 자동차의 정보들을 나타낸 모델
 */
data class CarModel(
    val modelName: String, // 모델명
    val manufacture: String, // 제조사
    val productionYear: Int, // 생산년도
    val rentAvailable: Boolean, // 대여 가능 여부
)
