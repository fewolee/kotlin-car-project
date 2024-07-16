package com.carenation.car.adapter.out.persistence.mapper

import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

// Entity와 Dto간 매핑해주는 인터페이스
@Mapper(componentModel = "spring")
interface CarMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarMapper::class.java)
    }

//    // Car Entity를 CarInfoDto로 변환
//    fun toCarInfoDto(carEntity: CarEntity): CarInfoDto

    // Car Entity를 CreatedCarDto로 변환
    fun toCreatedCarDto(carEntity: CarEntity): CreatedCarResponse

//    // CarUpdatRequest Dto와 자동차의 id를 받아 UpdateCarDto로 변환
//    fun toUpdateCarDto(id: Long, req: CarUpdateRequest): UpdateCarDto
//
//    // Car Entity와 카테고리 리스트를 받아 UpdatedCarDto로 변환
//    fun toUpdatedCarDto(carEntity: CarEntity, categoryNames : List<String>) : UpdatedCarResponse

}