package com.carenation.car.domain.car.dto

import com.carenation.car.domain.car.util.NotBlankElementList
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class RegisterCarDto (
    @field:NotBlank(message = "모델명은 필수입니다")
    val modelName: String,

    @field:NotEmpty(message = "제조사는 필수입니다")
    val manufacture: String,

    @field:Positive(message = "양수만 가능합니다")
    @field:Max(value = 2024, message="생산년도는 2024년을 넘을 수 없습니다")
    val productionYear: Int,

    @field:NotNull(message = "대여가능 여부는 필수입니다")
    val rentAvailable: Boolean,

    @NotBlankElementList
    val categoryNames: List<String>
)