package com.carenation.car.port.out

import com.carenation.car.dto.CarUpdateInDto
import org.springframework.stereotype.Repository

/**
 * out port
 */
@Repository
interface CarUpdateRepository {
    fun updateCar(carUpdateInDto: CarUpdateInDto)

    fun updateCarCategory(
        carId: Long,
        categoryNames: List<String>,
    )
}
