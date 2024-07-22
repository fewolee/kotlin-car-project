package com.carenation.car.application.domain

/**
 * 자동차를 수정하기 위해 필요한 정보들을 담은 Dto
 */
data class CarUpdateInDto (
    val id : Long, // 자동차 엔티티 Pk

    val modelName: String, // 모델명

    val manufacture: String, // 제조사

    val productionYear: Int, // 생산년도

    val rentAvailable: Boolean, // 대여 가능 여부

    val categoryNames: List<String> // 자동차 카테고리
)