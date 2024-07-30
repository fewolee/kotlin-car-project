package com.carenation.car.connector.annotation

import com.carenation.car.connector.validator.NotExistsCategoryValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Pathvariable로 들어오는 자동차의 카테고리 검증
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotExistsCategoryValidator::class])
annotation class NotExistsCategory(
    val message: String = "존재하지 않는 자동차 카테고리입니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
