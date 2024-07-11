package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.request.CarInfoListRequest

interface CarReadService {

    fun getById(carId: Long): CarInfoDto
    fun getAll(): List<CarInfoDto>
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
    fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto>
}