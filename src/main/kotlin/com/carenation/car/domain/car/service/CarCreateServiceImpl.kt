package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.request.CreateCarRequest
import com.carenation.car.domain.car.dto.response.CreatedCarResponse
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.mapper.CarMapper
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategoryEntity
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarCreateServiceImpl (
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carMapper : CarMapper
): CarCreateService{

    // 자동차 등록
    @Transactional
    override fun register(
        req: CreateCarRequest
    ): CreatedCarResponse {

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


        return carMapper.toRegisteredCarDto(car)

    }
}