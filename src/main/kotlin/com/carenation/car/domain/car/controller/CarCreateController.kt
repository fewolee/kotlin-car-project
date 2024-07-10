package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto
import com.carenation.car.domain.car.service.CarCreateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/cars")
class CarCreateController(
    private val carCreateServiceImpl: CarCreateService
) {

    //자동차 등록
    @PostMapping
    fun register(@Valid @RequestBody registerCarDto: RegisterCarDto): ResponseEntity<RegisteredCarDto> {

        return ResponseEntity(carCreateServiceImpl.register(registerCarDto), HttpStatus.CREATED)
    }


}