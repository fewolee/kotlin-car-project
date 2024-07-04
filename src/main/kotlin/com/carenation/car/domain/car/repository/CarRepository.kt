package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.category.entity.CarCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    fun findByManufactureAndModelNameAndProductionYear(manufacture: String, modelName: String, productionYear: Int): List<Car>
    // 제조사, 모델명, 생산년도로 검색

    fun findByIdIn(ids : List<Long>) : List<Car>


}