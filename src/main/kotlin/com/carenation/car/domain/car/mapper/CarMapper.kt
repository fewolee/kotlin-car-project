package com.carenation.car.domain.car.mapper

import com.carenation.car.domain.car.dto.*
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.request.CarUpdateRequest
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface CarMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarMapper::class.java)
    }

    fun toCarInfoDto(carEntity: CarEntity): CarInfoDto

    fun toRegisteredCarDto(carEntity: CarEntity): CreatedCarResponse

    fun toUpdateCarDto(id: Long, req: CarUpdateRequest): UpdateCarDto

    fun toUpdatedCarDto(carEntity: CarEntity, categoryNames : List<String>) : UpdatedCarResponse
}