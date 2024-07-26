package com.carenation.car.port.out

import com.carenation.car.dto.CarCreateInDto

/**
 * out port
 */
interface CarCreateOutPort {
    // CarEntity 영속화
    fun saveCar(carCreateInDto: CarCreateInDto): Long?

    // CarCategory 영속화
    fun saveCarCategory(
        categoryNames: List<String>,
        carId: Long,
    )
}
