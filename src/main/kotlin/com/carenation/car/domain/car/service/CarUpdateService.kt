package com.carenation.car.domain.car.service


import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.response.UpdatedCarResponse

interface CarUpdateService {
    // 자동차 정보 수정
    fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse
}