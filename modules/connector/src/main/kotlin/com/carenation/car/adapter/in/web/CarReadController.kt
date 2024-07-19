package com.carenation.car.adapter.`in`.web

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.mapper.CarInMapper
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import jakarta.validation.constraints.NotNull

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarReadController(
    private val carReadUseCase: CarReadUseCase,
    private val carInMapper: CarInMapper
) {

    //자동차 id로 자동차 정보 조회
    @GetMapping("/{id}")
    fun getByCustomId(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long): ResponseEntity<CarInfoDto> {
        return ResponseEntity.ok(carReadUseCase.getById(id))
    }


    //모든 자동차 정보 조회
    @GetMapping
    fun getAll(): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getAll())
    }

    //카테고리로 자동차 정보 조회
    @GetMapping("/category/{category}")
    fun getByCategory(@PathVariable @NotNull(message ="카테고리는 필수입니다") category: String): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getByCategoryName(category))
    }



    //제조사, 모델명, 생산년도로 자동차 정보 조회
    @GetMapping("/")
    fun getByDynamicQuery(@ModelAttribute req: CarInfoListRequest): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadUseCase.getDynamicQuery(carInMapper.toCarInfoListInDto(req)))
    }


}