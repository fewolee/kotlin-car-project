package com.carenation.car.adapter.`in`.web

import com.carenation.car.adapter.`in`.dto.request.CarUpdateRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse
import com.carenation.car.adapter.out.persistence.mapper.CarMapper
import com.carenation.car.port.`in`.usecase.CarUseCase
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarUpdateController(
    private val carUseCase: CarUseCase,
    private val carMapper: CarMapper
) {

    // 자동차 정보 수정
    @PutMapping("/{id}")
    fun update(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long,
               @Valid @RequestBody req: CarUpdateRequest
    ): ResponseEntity<UpdatedCarResponse> {
        val updateCarDto = carMapper.toUpdateCarDto(id, req)
        return ResponseEntity.ok(carUseCase.update(updateCarDto))
    }

}