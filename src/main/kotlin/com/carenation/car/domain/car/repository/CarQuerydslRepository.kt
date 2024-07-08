package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.dto.CarInfoDto

interface CarQuerydslRepository {

    fun findByCustomId(carId: Long): CarInfoDto
}