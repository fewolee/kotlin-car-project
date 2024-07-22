package com.carenation.car.application.domain

/**
 * 카테고리를 제외한 자동차의 정보들을 나타낸 Dto
 */
data class CarInfoDto (
    val modelName: String, // 모델명

    val manufacture: String, // 제조사

    val productionYear: Int, // 생산년도

    val rentAvailable: Boolean // 대여 가능 여부
)