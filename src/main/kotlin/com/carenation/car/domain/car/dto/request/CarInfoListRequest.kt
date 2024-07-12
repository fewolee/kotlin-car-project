package com.carenation.car.domain.car.dto.request

data class CarInfoListRequest(
    val modelName: String?,
    val manufacture: String?,
    val productionYear: Int?
)