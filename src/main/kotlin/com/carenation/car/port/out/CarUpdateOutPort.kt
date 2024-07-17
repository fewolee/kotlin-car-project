package com.carenation.car.port.out

import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse

// 내부 영역이 외부 영역으로 사용하기 위한 통로
interface CarUpdateOutPort {

    // 자동차 정보 수정
    fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse


}