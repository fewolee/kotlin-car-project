package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.response.UpdatedCarResponse
import com.carenation.car.domain.car.mapper.CarMapper
import com.carenation.car.domain.car.dto.request.CarUpdateRequest
import com.carenation.car.domain.car.service.CarUpdateService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@Validated
@RequestMapping("/cars")
class CarUpdateController(
    private val carUpdateServiceImpl: CarUpdateService,
    private val carMapper : CarMapper
) {

    // 자동차 정보 수정
    @PutMapping("/{id}")
    fun update(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long,
               @Valid @RequestBody req: CarUpdateRequest
    ): ResponseEntity<UpdatedCarResponse> {
        val updateCarDto = carMapper.toUpdateCarDto(id, req)
        return ResponseEntity.ok(carUpdateServiceImpl.update(updateCarDto))
    }


}