package com.carenation.car.application.domain


// 생성된 자동차의 정보들을 반환하는 dto
data class CarCreateOutDto (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String> // 자동차 카테고리
)