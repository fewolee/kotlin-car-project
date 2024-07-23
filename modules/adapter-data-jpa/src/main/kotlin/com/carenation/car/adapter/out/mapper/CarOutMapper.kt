package com.carenation.car.adapter.out.mapper

import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.application.domain.CarUpdateOutDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers



/**
 * Adapter로 나가는 Dto를 사용하기 위해 Entiy를 Dto로 바꿔주기 위한 매퍼 인터페이스
 */
@Mapper(componentModel = "spring")
interface CarOutMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarOutMapper::class.java)
    }

    // Car Entity를 CarInfoDto로 변환
    fun toCarInfoDto(carEntity: CarEntity): CarInfoDto

    // Car Entity와 카테고리 리스트를 받아 CarUpdateOutDto로 변환
    fun toCarUpdateOutDto(carEntity: CarEntity, categoryNames : List<String>) : CarUpdateOutDto

}