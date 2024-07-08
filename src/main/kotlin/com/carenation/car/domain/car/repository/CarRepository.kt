package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<CarEntity, Long> ,CarQuerydslRepository{
    fun findByManufactureAndModelNameAndProductionYear(manufacture: String, modelName: String, productionYear: Int): List<CarEntity>
    // 제조사, 모델명, 생산년도로 검색

    fun findByIdIn(ids : List<Long>) : List<CarEntity>

    @Modifying
    @Query(value = "delete from CarEntity where id = :id")
    fun deleteByCarEntityId(id: Long)

}