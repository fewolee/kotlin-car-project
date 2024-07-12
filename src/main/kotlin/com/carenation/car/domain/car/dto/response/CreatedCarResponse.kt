package com.carenation.car.domain.car.dto.response

// 생성된 자동차의 정보들을 반환하는 Dto
data class CreatedCarResponse (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)