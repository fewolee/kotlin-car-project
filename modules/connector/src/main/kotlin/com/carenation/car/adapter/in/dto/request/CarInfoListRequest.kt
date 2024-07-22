package com.carenation.car.adapter.`in`.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive

/**
 * 모델명, 제조사, 생산년도로 자동차를 동적으로 조회하기 위해 요청을 보내기 위한 Dto
 */
data class CarInfoListRequest(
    @field:NotBlank(message = "모델명은 필수입니다")
    val modelName: String?, // 모델명

    @field:NotEmpty(message = "제조사는 필수입니다")
    val manufacture: String?, // 제조사

    @field:Positive(message = "양수만 가능합니다")
    @field:Max(value = 2024, message="생산년도는 2024년을 넘을 수 없습니다")
    val productionYear: Int? // 생산년도
)


