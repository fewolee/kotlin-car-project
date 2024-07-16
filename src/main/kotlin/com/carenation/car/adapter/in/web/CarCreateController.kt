package com.carenation.car.adapter.`in`.web

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.port.`in`.usecase.CarUseCase
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
    private val carUseCase: CarUseCase
) {

    // 자동차 등록
    @PostMapping
    fun create(@Valid @RequestBody createCarRequest: CreateCarRequest): ResponseEntity<CreatedCarResponse> {

        return ResponseEntity(carUseCase.create(createCarRequest), HttpStatus.CREATED)
    }


}