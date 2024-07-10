package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.service.CarDeleteService
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
@RequestMapping("/cars")
class CarDeleteController (
    private val carDeleteServiceImpl: CarDeleteService
){
    // 자동차 삭제
    @DeleteMapping("/{id}")
    fun delete(@PathVariable @NotNull(message ="Car ID는 필수입니다") id : Long)  {
        carDeleteServiceImpl.delete(id)
    }

}