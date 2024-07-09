package com.carenation.car.domain.car.request

data class CarUpdateRequest(
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)