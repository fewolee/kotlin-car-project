package com.carenation.car.adapter.annotation

import com.carenation.car.adapter.validator.NotBlankElementListValidator
import jakarta.validation.Constraint
import kotlin.reflect.KClass

/**
 * List<String> 형의 데이터를 검증하기 위한 어노테이션 클래스
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotBlankElementListValidator::class])
annotation class NotBlankElementList(
    val message: String = "리스트 안에 빈 값이 존재합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = [],
)
