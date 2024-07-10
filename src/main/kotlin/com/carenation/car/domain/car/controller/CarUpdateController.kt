package com.carenation.car.domain.car.controller

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarDto
import com.carenation.car.domain.car.request.CarUpdateRequest
import com.carenation.car.domain.car.service.CarUpdateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cars")
class CarUpdateController(
    private val carUpdateServiceImpl: CarUpdateService
) {

    // 자동차 수정
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: CarUpdateRequest): ResponseEntity<UpdatedCarDto> {

        val updateCarDto = UpdateCarDto(
            id=id,
            modelName = req.modelName,
            manufacture = req.manufacture,
            productionYear = req.productionYear,
            rentAvailable = req.rentAvailable,
            categoryNames = req.categoryNames,
        )
        return ResponseEntity.ok(carUpdateServiceImpl.update(updateCarDto))

    }


}