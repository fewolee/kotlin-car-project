package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CarReadService(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
) : CarReadServiceBus{

    // id로 자동차 조회
    override fun getById(id: Long): CarInfoDto {
       var carEntity = carRepository.findById(id).orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: $id") }


        return CarInfoDto(
            modelName = carEntity.modelName,
            manufacture = carEntity.manufacture,
            productionYear = carEntity.productionYear,
            rentAvailable = carEntity.rentAvailable,

        )


    }

    // 모든 자동차 조회
    override fun getAll(): List<CarInfoDto> {
        return carRepository.findAll()
            .map { carEntity ->
                CarInfoDto(
                    modelName = carEntity.modelName,
                    manufacture = carEntity.manufacture,
                    productionYear = carEntity.productionYear,
                    rentAvailable = carEntity.rentAvailable
                )
            }
    }



    // 자동차 대여 가능 여부 조회
    override fun rentAvailable(carId: Long): Boolean {
        val car = carRepository.findById(carId).orElseThrow()
        return car.rentAvailable
    }

    // 카테고리로 자동차 조회
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        val category = categoryRepository.findByCategoryName(categoryName)
            ?: throw IllegalArgumentException("해당하는 카테고리 이름이 없습니다: $categoryName")

        val carCategories = carCategoryRepository.findByCategoryEntityId(category.id!!)
        val carIds = carCategories.map { it.carEntity.id!! }

        return carRepository.findByIdIn(carIds)
            .map { carEntity ->
                CarInfoDto(
                    modelName = carEntity.modelName,
                    manufacture = carEntity.manufacture,
                    productionYear = carEntity.productionYear,
                    rentAvailable = carEntity.rentAvailable
                )

            }
    }

    // 제조사, 모델명, 생산년도로 자동차 조회
    override fun getByManufactureModelNameProductionYear(manufacture: String, modelName: String, productionYear: Int): List<CarInfoDto> {
        val carEntity = carRepository.findByManufactureAndModelNameAndProductionYear(manufacture, modelName, productionYear)

        return carEntity
            .map { carEntity ->
                CarInfoDto(
                    modelName = carEntity.modelName,
                    manufacture = carEntity.manufacture,
                    productionYear = carEntity.productionYear,
                    rentAvailable = carEntity.rentAvailable
                ) }

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