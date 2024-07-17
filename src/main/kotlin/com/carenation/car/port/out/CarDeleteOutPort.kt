package com.carenation.car.port.out

// 내부 영역이 외부 영역으로 사용하기 위한 통로
interface CarDeleteOutPort {

    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)

}