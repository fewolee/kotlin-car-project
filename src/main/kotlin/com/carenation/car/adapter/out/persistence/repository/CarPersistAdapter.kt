package com.carenation.car.adapter.out.persistence.repository

import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.`in`.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.persistence.mapper.CarMapper
import com.carenation.car.port.out.CarOutport
import org.springframework.stereotype.Component

@Component
class CarPersistAdapter(

    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carMapper : CarMapper
) : CarOutport{

    override fun create(req: CreateCarRequest): CreatedCarResponse {
        //영속화 목적 entity로 변환
        var car = CarEntity(
            modelName = req.modelName,
            manufacture = req.manufacture,
            productionYear = req.productionYear,
            rentAvailable = req.rentAvailable
        )

        // 영속화
        val savedCar = carRepository.save(car)

        // 카테고리 영속화
        val categoryNames = mutableListOf<String>()
        req.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }


        // 생성한 Car 엔티티를 Mapper를 통해 CreatedCarDto로 반환
        return carMapper.toCreatedCarDto(car)


    }
}