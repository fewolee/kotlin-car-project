package com.carenation.car.port.out


/*
삭제 관련 인터페이스
 */
interface CarDeleteOutPort {

    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)

}