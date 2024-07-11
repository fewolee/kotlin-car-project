package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.request.CarInfoListRequest
import com.carenation.car.domain.car.service.CarReadService
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@Validated
@RequestMapping("/cars")
class CarReadController (
    private val carReadServiceImpl : CarReadService
){
    //자동차 id로 조회
    @GetMapping("/{id}")
    fun getByCustomId(@PathVariable @NotNull(message ="Car ID는 필수입니다") id: Long): ResponseEntity<CarInfoDto> {
        return ResponseEntity.ok(carReadServiceImpl.getById(id))
    }


    //모든 자동차 조회
    @GetMapping
    fun getAll(): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getAll())
    }


    //카테고리로 자동차 조회
    @GetMapping("/category/{category}")
    fun getByCategory(@PathVariable @NotNull(message ="카테고리는 필수입니다") category: String): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getByCategoryName(category))
    }



    //제조사, 모델명, 생산년도로 자동차 조회
    @GetMapping("/")
    fun getByDynamicQuery(@ModelAttribute req: CarInfoListRequest): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getDynamicQuery(req))
    }

}