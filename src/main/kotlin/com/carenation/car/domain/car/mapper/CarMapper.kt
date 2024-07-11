package com.carenation.car.domain.car.mapper

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto
import com.carenation.car.domain.car.entity.CarEntity
import org.mapstruct.InheritConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
@Mapper
interface CarMapper {

    @Mapping(source = "modelName", target = "modelName")
    fun toCarInfoDto(carEntity: CarEntity) : CarInfoDto

    @Mapping(source = "modelName", target = "modelName")
    fun toRegisteredCarDto(carEntity: CarEntity) : RegisteredCarDto

    @InheritConfiguration
    fun toCarEntity(registerCarDto: RegisterCarDto) : CarEntity
}