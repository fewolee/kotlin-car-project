package com.carenation.car.connector.validator

import com.carenation.car.application.port.`in`.usecase.CarReadUseCase
import com.carenation.car.connector.annotation.NotExistsCarId
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
