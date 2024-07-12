package com.carenation.car.domain.car.dto.response

class UpdatedCarResponse (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)