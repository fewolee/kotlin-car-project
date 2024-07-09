package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.service.CarReadServiceBus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<CarEntity, Long> , CarReadServiceBus {

    fun findByIdIn(ids : List<Long>) : List<CarEntity>

//    @Modifying
//    @Query(value = "delete from CarEntity where id = :id")
//    fun deleteByCarEntityId(id: Long)

    //fun findByManufactureAndModelNameAndProductionYear(manufacture: String, modelName: String, productionYear: Int): List<CarEntity>
    // 제조사, 모델명, 생산년도로 검색

}