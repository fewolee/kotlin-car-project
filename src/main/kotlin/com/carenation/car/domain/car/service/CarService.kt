package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.entity.Car
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategory
import com.carenation.car.domain.category.entity.Category
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CarService(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
) {
    // 자동차 등록
    fun registerCar(
        reguest: RegisterCarDto
    ): Car {
        var car = Car(
            modelName = reguest.modelName,
            manufacture = reguest.manufacture,
            productionYear = reguest.productionYear,
            rentAvailable = reguest.rentAvailable
        )

        val savedCar = carRepository.save(car)

        reguest.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategory = CarCategory(car = savedCar, category = category)
            carCategoryRepository.save(carCategory)
        }

        return savedCar
    }

    // id로 자동차 조회
    fun getCarById(id: Long): Car {
        return carRepository.findById(id).orElseThrow { IllegalArgumentException("Car not found with id: $id") }
    }

    // 모든 자동차 조회
    fun getAllCars(): List<Car> {
        return carRepository.findAll()
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



}






















//    // 자동차 정보 수정
//    fun updateCar(id: Long, updatedCar: Car): Car {
//        val car = getCarById(id)
//        return carRepository.save(car.copy(
//            manufacture = updatedCar.manufacture,
//            modelName = updatedCar.modelName,
//            productionYear = updatedCar.productionYear,
//            categories = updatedCar.categories,
//            rentAvailable = updatedCar.rentAvailable
//        ))
//    }
