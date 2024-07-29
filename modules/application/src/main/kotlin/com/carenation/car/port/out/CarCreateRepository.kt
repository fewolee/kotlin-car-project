package com.carenation.car.port.out

import com.carenation.car.dto.CarCreateInDto
import org.springframework.stereotype.Repository

/**
 * out port
 */
@Repository
interface CarCreateRepository {
    // CarEntity 영속화
    fun saveCar(carCreateInDto: CarCreateInDto): Long?

    // CarCategory 영속화
    fun saveCarCategory(
        categoryNames: List<String>,
        carId: Long,
    )
}
