package com.carenation.car.domain.car.service


interface CarDeleteService {
    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)
}