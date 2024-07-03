package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.car.enum.CarCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    fun findByCategories(category: CarCategory): List<Car> //자동차 카테고리로 검색
    fun findByManufactureAndModelNameAndProductionYear(manufacture: String, modelName: String, productionYear: Int): List<Car>
    fun isRentAvailable(carId: Long): Boolean
    // 제조사, 모델명, 생산년도로 검색
    fun getAllCars(): List<Car> // 모든 자동차 조회

}