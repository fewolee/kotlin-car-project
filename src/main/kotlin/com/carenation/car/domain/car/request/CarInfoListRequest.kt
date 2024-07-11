package com.carenation.car.domain.car.request

data class CarInfoListRequest(
    val modelName: String?,
    val manufacture: String?,
    val productionYear: Int?
)