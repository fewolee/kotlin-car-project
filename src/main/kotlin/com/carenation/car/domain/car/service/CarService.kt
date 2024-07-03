package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.car.enum.CarCategory
import com.carenation.car.domain.car.repository.CarRepository
import org.springframework.stereotype.Service

@Service
class CarService(
    private val carRepository: CarRepository
) {
    // 자동차 등록
    fun registerCar(car: Car): Car {
        return carRepository.save(car)
    }

    // id로 자동차 조회
    fun getCarById(id: Long): Car {
        return carRepository.findById(id).orElseThrow { IllegalArgumentException("Car not found with id: $id") }
    }

    // 모든 자동차 조회
    fun getAllCars(): List<Car> {
        return carRepository.findAll()
    }

    // 카테고리로 자동차 조회
    fun getCarsByCategory(category: CarCategory): List<Car> {
        return carRepository.findByCategories(category)
    }

    // 자동차 대여 가능 여부 조회
    fun rentAvailable(carId: Long): Boolean {
        val car = carRepository.findById(carId).orElseThrow()
        return car.rentAvailable
    }
    // 제조사, 모델명, 생산년도로 자동차 조회
    fun getCarsByManufactureModelNameProductionYear(manufacture: String, modelName: String, productionYear: Int): List<Car> {
        return carRepository.findByManufactureAndModelNameAndProductionYear(manufacture, modelName, productionYear)
    }


    // 자동차 정보 수정
    fun updateCar(id: Long, updatedCar: Car): Car {
        val car = getCarById(id)
        return carRepository.save(car.copy(
            manufacture = updatedCar.manufacture,
            modelName = updatedCar.modelName,
            productionYear = updatedCar.productionYear,
            categories = updatedCar.categories,
            rentAvailable = updatedCar.rentAvailable
        ))
    }
}