package com.carenation.car.adapter.`in`.web

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.mapper.CarInMapper
import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.port.`in`.usecase.CarCreateUseCase
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
    private val carCreateUseCase: CarCreateUseCase,
    private val carInMapper: CarInMapper
) {

    // 자동차 등록
    @PostMapping
    fun create(@Valid @RequestBody createCarRequest: CreateCarRequest): ResponseEntity<CarCreateOutDto> {

        return ResponseEntity(carCreateUseCase.create(carInMapper.toCarAllInfo(createCarRequest)), HttpStatus.CREATED)
    }


}