package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.service.CarReadService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cars")
class CarReadController (
    private val carReadServiceImpl : CarReadService
){
    //자동차 id로 조회
    @GetMapping("/{id}")
    fun getByCustomId(@PathVariable id: Long): ResponseEntity<CarInfoDto> {
        return ResponseEntity.ok(carReadServiceImpl.getById(id))
    }


    //모든 자동차 조회
    @GetMapping
    fun getAll(): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getAll())
    }


    //카테고리로 자동차 조회
    @GetMapping("/category/{category}")
    fun getByCategory(@PathVariable category: String): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getByCategoryName(category))
    }



    //제조사, 모델명, 생산년도로 자동차 조회
    @GetMapping("/")
    fun getByDynamicQuery(
        @RequestParam modelName: String?,
        @RequestParam manufacture: String?,
        @RequestParam productionYear: Int?
    ): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadServiceImpl.getDynamicQuery(modelName, manufacture, productionYear))
    }

}