package com.carenation.car.adapter.`in`.util

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotBlankElementListValidator : ConstraintValidator<NotBlankElementList, List<String>> {
    override fun isValid(lists: List<String>?, context: ConstraintValidatorContext?): Boolean {
        if (lists == null) {
            return false
        }
        return lists.none {it.isNullOrBlank() }
    }
}