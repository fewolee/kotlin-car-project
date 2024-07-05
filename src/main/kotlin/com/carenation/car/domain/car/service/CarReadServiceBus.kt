package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto

interface CarReadServiceBus {
    fun getById(id: Long): CarInfoDto
    fun getAll() : List<CarInfoDto>
    fun rentAvailable(carId: Long): Boolean
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
    fun getByManufactureModelNameProductionYear(manufacture: String, modelName: String, productionYear: Int): List<CarInfoDto>
}