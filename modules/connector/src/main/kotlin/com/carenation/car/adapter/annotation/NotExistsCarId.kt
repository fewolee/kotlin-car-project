package com.carenation.car.adapter.annotation

import com.carenation.car.adapter.validator.NotExistsCarIdValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Pathvariable로 들어오는 자동차의 id 검증
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotExistsCarIdValidator::class])
annotation class NotExistsCarId(
    val message: String = "존재하지 않는 자동차 ID입니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
