package com.carenation.car.domain.car.dto.response

// 수정된 자동차의 정보들을 반환하는 Dto
class UpdatedCarResponse (
    val modelName: String,
    val manufacture: String,
    val productionYear: Int,
    val rentAvailable: Boolean,
    val categoryNames: List<String>
)