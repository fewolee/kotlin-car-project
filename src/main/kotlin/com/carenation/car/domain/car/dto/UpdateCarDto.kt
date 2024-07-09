package com.carenation.car.domain.car.dto

data class UpdateCarDto (
    val id : Long,
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)