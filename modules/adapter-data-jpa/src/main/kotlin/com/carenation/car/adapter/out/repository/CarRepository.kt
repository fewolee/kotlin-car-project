package com.carenation.car.adapter.out.repository

import com.carenation.car.adapter.out.persistence.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * JpaRepository와 CarQuerydslRepository 인터페이스를 Implement한 Car 리포지토리
 */
@Repository
interface CarRepository : JpaRepository<CarEntity, Long>, CarQuerydslRepository {
}