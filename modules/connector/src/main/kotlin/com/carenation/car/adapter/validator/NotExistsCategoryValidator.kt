package com.carenation.car.adapter.validator

import com.carenation.car.adapter.annotation.NotExistsCategory
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotExistsCategoryValidator(
    private val carReadUseCase: CarReadUseCase,
) : ConstraintValidator<NotExistsCategory, String> {
    override fun isValid(
        category: String,
        context: ConstraintValidatorContext?,
    ): Boolean = carReadUseCase.getByCategoryName(category) != null
}
