package com.carenation.car.domain.category.service

import com.carenation.car.domain.car.dto.CarInfoDto

interface CategoryReadServiceBus {
    fun getByCategoryName(categoryName: String): List<CarInfoDto>
}