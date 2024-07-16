package com.carenation.car.adapter.out.dto.response


// 생성된 자동차의 정보들을 반환하는 dto
data class CreatedCarResponse (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean
)