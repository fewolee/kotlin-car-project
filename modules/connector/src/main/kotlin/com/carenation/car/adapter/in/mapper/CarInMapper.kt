package com.carenation.car.adapter.`in`.mapper

import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.dto.request.CarUpdateRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.application.domain.*
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

// Entity와 Dto간 매핑해주는 인터페이스
@Mapper(componentModel = "spring")
interface CarInMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarInMapper::class.java)
    }

    // CarUpdatRequest Dto와 자동차의 id를 받아 UpdateCarDto로 변환
    fun toCarUpdateInDto(id: Long, req: CarUpdateRequest): CarUpdateInDto

    //CreateCarRequest를 CarInfo 로 변환
    fun toCarAllInfo(createCarRequest: CreateCarRequest) : CarAllInfoDto

    //carInfoListRequest를 CarInfoListInDto로 변환
    fun toCarInfoListInDto(carInfoListRequest: CarInfoListRequest) : CarInfoListInDto
}