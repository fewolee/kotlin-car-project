package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.dto.CarInfoDto

interface CarQuerydslRepository {

    fun getById(carId: Long): CarInfoDto
    fun getAll() : List<CarInfoDto>
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
    fun getDynamicQuery(modelName: String?, manufacture : String?, productionYear : Int?) : List<CarInfoDto>
}