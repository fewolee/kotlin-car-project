package com.carenation.car.port.out


/**
 * out port
 */
interface CarDeleteOutPort {
    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)
}