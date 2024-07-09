package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.querydsl.core.types.dsl.BooleanExpression

interface CarReadService {

    fun getById(carId: Long): CarInfoDto
    fun getAll() : List<CarInfoDto>
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
    fun getDynamicQuery(modelName: String?, manufacture : String?, productionYear : Int?) : List<CarInfoDto>
}