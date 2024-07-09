package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.*
import com.carenation.car.domain.car.service.CarReadServiceBus
import com.carenation.car.domain.car.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(
    private val carCreateService: CarCreateServiceBus,
    private val carDeleteService: CarDeleteServiceBus,
    private val carReadService: CarReadServiceBus,
    private val carUpdateService: CarUpdateServiceBus
) {

    //자동차 등록
    @PostMapping
    fun register(@RequestBody registerCarDto: RegisterCarDto): ResponseEntity<RegisteredCarDto> {
        return ResponseEntity(carCreateService.register(registerCarDto), HttpStatus.CREATED)
    }

    // 자동차 수정
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,@RequestBody updateCarDto: UpdateCarDto): ResponseEntity<UpdatedCarDto> {

        return ResponseEntity.ok(carUpdateService.update(id, updateCarDto))

    }

    // 자동차 삭제
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Long)  {
        carDeleteService.delete(id)
    }

    //자동차 id로 조회
    @GetMapping("/{id}")
    fun getByCustomId(@PathVariable id: Long): ResponseEntity<CarInfoDto> {
        return ResponseEntity.ok(carReadService.getById(id))
    }


    //모든 자동차 조회
    @GetMapping
    fun getAll(): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadService.getAll())
    }


    //카테고리로 자동차 조회
    @GetMapping("/category/{category}")
    fun getByCategory(@PathVariable category: String): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadService.getByCategoryName(category))
    }



    //제조사, 모델명, 생산년도로 자동차 조회
    @GetMapping("/")
    fun getByDynamicQuery(
        @RequestParam modelName: String?,
        @RequestParam manufacture: String?,
        @RequestParam productionYear: Int?
    ): ResponseEntity<List<CarInfoDto>> {
        return ResponseEntity.ok(carReadService.getDynamicQuery(modelName, manufacture, productionYear))
    }

    //    //자동차 id로 조회
//    @GetMapping("/{id}")
//    fun getById(@PathVariable id: Long): ResponseEntity<CarInfoDto> {
//        return ResponseEntity.ok(carReadService.getById(id))
//    }

    //    //자동차 대여 가능 여부 조회
//    @GetMapping("/{id}/available")
//    fun isCarAvailable(@PathVariable id: Long): ResponseEntity<Boolean> {
//        return ResponseEntity.ok(carReadService.rentAvailable(id))
//    }

}