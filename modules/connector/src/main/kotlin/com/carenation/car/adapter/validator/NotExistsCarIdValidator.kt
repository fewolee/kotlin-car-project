package com.carenation.car.adapter.validator

import com.carenation.car.adapter.annotation.NotExistsCarId
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotExistsCarIdValidator(
    private val carReadUseCase: CarReadUseCase,
) : ConstraintValidator<NotExistsCarId, Long> {
    override fun isValid(
        carId: Long,
        context: ConstraintValidatorContext?,
    ): Boolean = carReadUseCase.getById(carId) != null
}
