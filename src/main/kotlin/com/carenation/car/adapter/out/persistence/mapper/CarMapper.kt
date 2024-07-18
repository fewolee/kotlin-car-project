package com.carenation.car.adapter.out.persistence.mapper

import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.dto.request.CarUpdateRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.application.domain.*
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

// Entity와 Dto간 매핑해주는 인터페이스
@Mapper(componentModel = "spring")
interface CarMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarMapper::class.java)
    }

    // Car Entity를 CarInfoDto로 변환
    fun toCarInfoDto(carEntity: CarEntity): CarInfoDto

    // CarUpdatRequest Dto와 자동차의 id를 받아 UpdateCarDto로 변환
    fun toCarUpdateInDto(id: Long, req: CarUpdateRequest): CarUpdateInDto

    // Car Entity와 카테고리 리스트를 받아 CarUpdateOutDto로 변환
    fun toCarUpdateOutDto(carEntity: CarEntity, categoryNames : List<String>) : CarUpdateOutDto

    //CreateCarRequest를 CarInfo 로 변환
    fun toCarAllInfo(createCarRequest: CreateCarRequest) : CarAllInfo

    fun toCarInfoListInDto(carInfoListRequest: CarInfoListRequest) : CarInfoListInDto
}