package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.repository.CarRepository
import com.querydsl.core.types.dsl.BooleanExpression
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarReadService(
    private val carRepository: CarRepository
) : CarReadServiceBus {
    //id로 자동차 조회
    @Transactional
    override fun getById(carId: Long): CarInfoDto {

        return carRepository.getById(carId)

    }

    // 모든 자동차 조회
    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carRepository.getAll()
    }


     //자동차 대여 가능 여부 조회
    @Transactional
     fun rentAvailable(carId: Long): Boolean {
         var carEntity = carRepository.getById(carId)
         return carEntity.rentAvailable
    }

    // 카테고리로 자동차 조회
    @Transactional
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carRepository.getByCategoryName(categoryName)
    }

    // 모델명, 제조사, 생산년도로 자동차 초회하는 동적 쿼리 메서드
    @Transactional
    override fun getDynamicQuery(modelName: String?, manufacture: String?, productionYear: Int?): List<CarInfoDto> {
        return  carRepository.getDynamicQuery(modelName = modelName, manufacture = manufacture, productionYear = productionYear)
    }

    // 모델명이 같은지 확인
    @Transactional
    override fun eqModelName(modelName: String?): BooleanExpression? {
        return carRepository.eqModelName(modelName)
    }

    // 제조사가 같은지 확인
    @Transactional
    override fun eqManufacture(manufacture: String?): BooleanExpression? {
        return carRepository.eqManufacture(manufacture)
    }

    // 생산년도가 같은지 확인
    @Transactional
    override fun eqProductionYear(productionYear: Int?): BooleanExpression? {
        return carRepository.eqProductionYear(productionYear)
    }



//    // 제조사, 모델명, 생산년도로 자동차 조회
//    @Transactional
//    override fun getByManufactureModelNameProductionYear(manufacture: String, modelName: String, productionYear: Int): List<CarInfoDto> {
//        val carEntity = carRepository.findByManufactureAndModelNameAndProductionYear(manufacture, modelName, productionYear)
//
//        return carEntity
//            .map { carEntity ->
//                CarInfoDto(
//                    modelName = carEntity.modelName,
//                    manufacture = carEntity.manufacture,
//                    productionYear = carEntity.productionYear,
//                    rentAvailable = carEntity.rentAvailable
//                )
//            }
//
//    }


    // id로 자동차 조회
//    @Transactional
//    override fun getById(id: Long): CarInfoDto {
//       var carEntity = carRepository.findById(id).orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: $id")}
//
//        val categoryNames = carEntity.carCategories.map { it.categoryEntity.categoryName }
//
//        return CarInfoDto(
//            modelName = carEntity.modelName,
//            manufacture = carEntity.manufacture,
//            productionYear = carEntity.productionYear,
//            rentAvailable = carEntity.rentAvailable,
//            categoryNames = categoryNames
//
//        )
//
//
//    }


//    // 모든 자동차 조회
//    @Transactional
//    override fun getAll(): List<CarInfoDto> {
//        return carRepository.findAll()
//            .map { carEntity ->
//            CarInfoDto(
//                modelName = carEntity.modelName,
//                manufacture = carEntity.manufacture,
//                productionYear = carEntity.productionYear,
//                rentAvailable = carEntity.rentAvailable
//            )
//        }
//
//    }



    //    // 카테고리로 자동차 조회
//    @Transactional
//    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
//        val category = categoryRepository.findByCategoryName(categoryName)
//            ?: throw IllegalArgumentException("해당하는 카테고리 이름이 없습니다: $categoryName")
//
//        val carCategories = carCategoryRepository.findByCategoryEntityId(category.id!!)
//        val carIds = carCategories.map { it.carEntity.id!! }
//
//        return carRepository.findByIdIn(carIds)
//            .map { carEntity ->
//                CarInfoDto(
//                    modelName = carEntity.modelName,
//                    manufacture = carEntity.manufacture,
//                    productionYear = carEntity.productionYear,
//                    rentAvailable = carEntity.rentAvailable
//                )
//
//            }
//    }


}
















