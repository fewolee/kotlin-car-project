package com.carenation.car.connector.validator

import com.carenation.car.application.port.`in`.usecase.CarReadUseCase
import com.carenation.car.connector.annotation.NotExistsCategory
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
