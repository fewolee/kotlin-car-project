package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<CarEntity, Long> , CarQuerydslRepository {

    // 자동차 Id 리스트로 자동차 리스트 조회
    fun findByIdIn(ids : List<Long>) : List<CarEntity>

}