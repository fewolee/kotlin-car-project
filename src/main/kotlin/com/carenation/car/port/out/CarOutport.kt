package com.carenation.car.port.out

import com.carenation.car.adapter.`in`.dto.CarInfoDto
import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse

// 내부 영역이 외부 영역으로 사용하기 위한 통로
interface CarOutport {
    fun create(createCarRequest: CreateCarRequest) : CreatedCarResponse

    // 자동차의 id로 자동차 엔티티를 조회해 CarInfoDto로 반환
    fun getById(carId: Long): CarInfoDto

    // 자동차의 모든 엔티티를 조회해 CarInfoDto 리스트로 반환
    fun getAll(): List<CarInfoDto>


    // 카테고리 이름으로 자동차 엔티티를 조회해 CarInfDto 리스트로 반환
    fun getByCategoryName(categoryName: String): List<CarInfoDto>

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarInfoDto 리스트로 반환
    fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto>

    // 자동차 정보 수정
    fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse

    // 자동차 id로 엔티티 삭제
    fun delete(carId: Long)





}