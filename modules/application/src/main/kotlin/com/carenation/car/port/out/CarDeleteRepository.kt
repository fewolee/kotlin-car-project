package com.carenation.car.port.out

import org.springframework.stereotype.Repository

/**
 * out port
 */
@Repository
interface CarDeleteRepository {
    // 자동차 id로 엔티티 삭제
    fun deleteCar(carId: Long)

    fun deleteCarCategory(carId: Long)
}
