package com.carenation.car.domain.car.dto.response

data class CreatedCarResponse (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)