package com.carenation.car.application.domain

/**
 * 수정된 자동차의 정보들을 반환하기 위한 정보들을 나타낸 Dto
 */
data class CarUpdateOutDto (
    val modelName: String, // 모델명

    val manufacture: String, // 제조사

    val productionYear: Int, // 생산년도

    val rentAvailable: Boolean, // 대여 가능 여부

    val categoryNames: List<String> // 자동차 카테고리
)