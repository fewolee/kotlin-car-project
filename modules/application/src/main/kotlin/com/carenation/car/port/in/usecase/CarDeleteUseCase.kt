package com.carenation.car.port.`in`.usecase

/***
input port
 ***/
interface CarDeleteUseCase {

    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)

}