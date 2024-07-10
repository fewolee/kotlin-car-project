package com.carenation.car.domain.car.util

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotBlankElementListValidator::class])
annotation class NotBlankElementList(
    val message: String = "리스트 안에 빈 값이 존재합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = [],)
