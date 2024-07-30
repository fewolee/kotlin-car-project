package com.carenation.car.application.port.out

import com.carenation.car.application.dto.CarUpdateInDto
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
