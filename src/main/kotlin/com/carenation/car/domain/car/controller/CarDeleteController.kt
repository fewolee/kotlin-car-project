package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.service.CarDeleteService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/cars")
class CarDeleteController (
    private val carDeleteServiceImpl: CarDeleteService
){
    // 자동차 삭제
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Long)  {
        carDeleteServiceImpl.delete(id)
    }

}