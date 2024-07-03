package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.car.enum.CarCategory
import com.carenation.car.domain.car.service.CarService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(
    private val carService: CarService
) {
    // 자동차 등록
    @PostMapping
    fun registerCar(@RequestBody car: Car): ResponseEntity<Car> {
        return ResponseEntity.ok(carService.registerCar(car))
    }
    //자동차 id로 조회
    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): ResponseEntity<Car> {
        return ResponseEntity.ok(carService.getCarById(id))
    }

    //모든 자동차 조회
    @GetMapping
    fun getAllCars(): ResponseEntity<List<Car>> {
        return ResponseEntity.ok(carService.getAllCars())
    }

    //카테고리로 자동차 조회
    @GetMapping("/categories/{category}")
    fun getCarsByCategory(@PathVariable category: CarCategory): ResponseEntity<List<Car>> {
        return ResponseEntity.ok(carService.getCarsByCategory(category))
    }

   //자동차 대여 가능 여부 조회
    @GetMapping("/{carId}/available")
    fun isCarAvailable(@PathVariable carId: Long): ResponseEntity<Boolean> {
        return ResponseEntity.ok(carService.rentAvailable(carId))
    }

    //제조사, 모델명, 생산년도로 자동차 조회
    @GetMapping("/detail")
    fun getCarsByManufactureModelNameProductionYear(
        @RequestParam manufacture: String,
        @RequestParam modelName: String,
        @RequestParam productionYear: Int
    ): ResponseEntity<List<Car>> {
        return ResponseEntity.ok(carService.getCarsByManufactureModelNameProductionYear(manufacture, modelName, productionYear))
    }


    @PutMapping("/{id}")
    fun updateCar(@PathVariable id: Long, @RequestBody updatedCar: Car): ResponseEntity<Car> {
        return ResponseEntity.ok(carService.updateCar(id, updatedCar))
    }
}
