package com.carenation.car.adapter.`in`.web

import com.carenation.car.adapter.`in`.dto.request.CarUpdateRequest
import com.carenation.car.adapter.`in`.mapper.CarInMapper
import com.carenation.car.application.domain.CarUpdateOutDto
import com.carenation.car.port.`in`.usecase.CarUpdateUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
@Tag(name = "Car-Update", description = "자동차 수정 관련 API입니다")
class CarUpdateController(
    private val carUpdateUseCase: CarUpdateUseCase,
    private val carInMapper: CarInMapper
) {

    // 자동차 정보 수정
    @PutMapping("/{id}")
    @Operation(summary = "자동차를 정보를 수정하는 API입니다", description = "자동차의 ID와 수정할 정보들을 입력받아 해당 자동차의 정보를 수정합니다")
    fun update(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long,
               @Valid @RequestBody req: CarUpdateRequest
    ): ResponseEntity<CarUpdateOutDto> {
        val carUpdateInDto = carInMapper.toCarUpdateInDto(id, req)
        return ResponseEntity.ok(carUpdateUseCase.update(carUpdateInDto))
    }

}