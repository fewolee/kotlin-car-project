package com.carenation.car.domain

/**
 * 카테고리를 포함한 자동차의 모든 정보를 나타내기 위한 모델
 */
data class CarDetailModel(
    val modelName: String, // 모델명
    val manufacture: String, // 제조사
    val productionYear: Int, // 생산년도
    val rentAvailable: Boolean, // 대여 가능 여부
    val categoryNames: List<String>, // 자동차 카테고리
)
