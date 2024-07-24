package com.carenation.car.adapter.validator

import com.carenation.car.adapter.annotation.NotBlankElementList
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotBlankElementListValidator : ConstraintValidator<NotBlankElementList, List<String>> {
    override fun isValid(
        lists: List<String>?,
        context: ConstraintValidatorContext?,
    ): Boolean {
        if (lists == null) {
            return false
        }
        return lists.none { it.isNullOrBlank() }
    }
}
