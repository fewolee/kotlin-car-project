package com.carenation.car.adapter.`in`.web

import com.carenation.car.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.port.`in`.usecase.CarDeleteUseCase
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarDeleteController(
    private val carDeleteUseCase: CarDeleteUseCase
) {

    // 자동차 삭제
    @DeleteMapping("/{id}")
    fun delete(@PathVariable @NotNull(message ="Car ID는 필수입니다") id : Long)  {
        carDeleteUseCase.delete(id)
    }


}