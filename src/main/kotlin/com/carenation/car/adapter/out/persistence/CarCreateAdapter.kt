package com.carenation.car.adapter.out.persistence

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.persistence.repository.CarCategoryRepository
import com.carenation.car.adapter.out.persistence.repository.CarRepository
import com.carenation.car.adapter.out.persistence.repository.CategoryRepository
import com.carenation.car.application.domain.CarAllInfo
import com.carenation.car.port.out.CarCreateOutPort
import org.springframework.stereotype.Component

/*
CarCreateOutPort의 구현체
 */
@Component
class CarCreateAdapter(

    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
) : CarCreateOutPort{

    override fun create(carAllInfo: CarAllInfo): CarCreateOutDto {
        //영속화 목적 entity로 변환
        var car = CarEntity(
            modelName = carAllInfo.modelName,
            manufacture = carAllInfo.manufacture,
            productionYear = carAllInfo.productionYear,
            rentAvailable = carAllInfo.rentAvailable
        )

        // 영속화
        val savedCar = carRepository.save(car)

        // 카테고리 영속화
        val categoryNames = mutableListOf<String>()
        carAllInfo.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }


        // 생성한 Car 정보들을 CarCreateOutDto로 변환
        return CarCreateOutDto(savedCar.modelName,
            savedCar.manufacture,
            savedCar.productionYear,
            savedCar.rentAvailable,
            categoryNames)

    }

}