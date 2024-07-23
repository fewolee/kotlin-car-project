package com.carenation.car.adapter.out.persistence

import com.carenation.car.application.domain.CarCreateOutDto
import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.repository.CarCategoryRepository
import com.carenation.car.adapter.out.repository.CarRepository
import com.carenation.car.adapter.out.repository.CategoryRepository
import com.carenation.car.application.domain.CarAllInfoDto
import com.carenation.car.port.out.CarCreateOutPort
import org.springframework.stereotype.Component

/**
 * CarCreateOutPort의 구현체
 */
@Component
class CarCreateAdapter(

    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
) : CarCreateOutPort{

    /**
     * 자동차 생성
     * @param CarAllInfoDto
     * @return CarCreateOutDto
     */
    override fun create(carAllInfoDto: CarAllInfoDto): CarCreateOutDto {
        //영속화 목적 entity로 변환
        var car = CarEntity(
            modelName = carAllInfoDto.modelName,
            manufacture = carAllInfoDto.manufacture,
            productionYear = carAllInfoDto.productionYear,
            rentAvailable = carAllInfoDto.rentAvailable
        )

        // 영속화
        val savedCar = carRepository.save(car)

        // 카테고리 영속화
        // CarAllInfoDto의 List<String>형의 카테고리를 반복을 통해 저장
        val categoryNames = mutableListOf<String>()
        carAllInfoDto.categoryNames.forEach() { categoryName ->
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