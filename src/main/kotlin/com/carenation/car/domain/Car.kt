package com.carenation.car.domain


data class Car (

    val id: Long? =null,

    val modelName: String,

    val manufacture: String,

    val productionYear: Int,

    val rentAvailable: Boolean,

    val categoryNames: List<String> // 자동차 카테고리
)