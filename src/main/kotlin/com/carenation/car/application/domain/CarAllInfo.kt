package com.carenation.car.application.domain

// 카테고리를 포함한 자동차의 모든 정보
data class CarAllInfo (

    val modelName: String,

    val manufacture: String,

    val productionYear: Int,

    val rentAvailable: Boolean,

    val categoryNames: List<String> // 자동차 카테고리
)