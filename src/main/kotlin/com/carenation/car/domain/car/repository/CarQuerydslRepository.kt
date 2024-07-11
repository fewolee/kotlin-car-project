package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.request.CarInfoListRequest

interface CarQuerydslRepository {

    fun getById(carId: Long): CarInfoDto
    fun getAll() : List<CarInfoDto>
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
    fun getDynamicQuery(req : CarInfoListRequest) : List<CarInfoDto>
}