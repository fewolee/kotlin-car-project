package com.carenation.car.adapter.`in`.web

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.mapper.CarInMapper
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
@Tag(name = "Car-Read", description = "자동차 조회 관련 API입니다")
class CarReadController(
    private val carReadUseCase: CarReadUseCase,
    private val carInMapper: CarInMapper
) {

    //자동차 id로 자동차 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "자동차의 ID로 단건 조회하는 API입니다", description = "자동차의 ID를 입력받아 해당하는 자동차의 정보를 조회합니다")
    fun getByCustomId(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long): ResponseEntity<CarInfoDto> {
        return ResponseEntity.ok(carReadUseCase.getById(id))
    }


    //모든 자동차 정보 조회
    @GetMapping
    @Operation(summary = "모든 자동차를 조회하는 API입니다", description = "모든 자동차의 정보들을 조회합니다")
    fun getAll(): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getAll())
    }

    //카테고리로 자동차 정보 조회
    @GetMapping("/category/{category}")
    @Operation(summary = "카테고리로 자동차를 조회합니다", description = "카테고리를 입력받아 해당 카테고리의 모든 자동차를 조회합니다")
    fun getByCategory(@PathVariable @NotNull(message ="카테고리는 필수입니다") category: String): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getByCategoryName(category))
    }



    //제조사, 모델명, 생산년도로 자동차 정보 조회
    @GetMapping("/")
    @Operation(summary = "자동차의 정보들로 단건 조회하는 API입니다", description = "자동차의 제조사,모델명,생산년도로 자동차 정보를 조회합니다")
    fun getByDynamicQuery(@Valid @ModelAttribute req: CarInfoListRequest): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getDynamicQuery(carInMapper.toCarInfoListInDto(req)))
    }


}