package com.carenation.car.domain.car.mapper

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto
import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.request.CarUpdateRequest
import org.mapstruct.InheritConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface CarMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarMapper::class.java)
    }

    fun toCarInfoDto(carEntity: CarEntity): CarInfoDto

    fun toRegisteredCarDto(carEntity: CarEntity): RegisteredCarDto

    fun toUpdateCarDto(id: Long, req: CarUpdateRequest): UpdateCarDto
}