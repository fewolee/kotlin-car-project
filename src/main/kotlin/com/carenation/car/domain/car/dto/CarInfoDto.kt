package com.carenation.car.domain.car.dto

data class CarInfoDto (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>?
)