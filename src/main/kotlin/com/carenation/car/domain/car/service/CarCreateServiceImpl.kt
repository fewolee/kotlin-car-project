package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.RegisterCarDto
import com.carenation.car.domain.car.dto.RegisteredCarDto
import com.carenation.car.domain.car.mapper.CarMapper
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategoryEntity
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class CarCreateServiceImpl (
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository
): CarCreateService{

    // 자동차 등록
    @Transactional
    override fun register(
        registerCarDto: RegisterCarDto
    ): RegisteredCarDto {

        val mapper = Mappers.getMapper(CarMapper::class.java)

         //영속화 목적 entity로 변환

//        var carEntity = CarEntity(
//            modelName = registerCarDto.modelName,
//            manufacture = registerCarDto.manufacture,
//            productionYear = registerCarDto.productionYear,
//            rentAvailable = registerCarDto.rentAvailable
//        )
        var carEntity = mapper.toCarEntity(registerCarDto)

        // 영속화
        val savedCar = carRepository.save(carEntity)
        
        // 카테고리 영속화
        val categoryNames = mutableListOf<String>()
        registerCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }


        return mapper.toRegisteredCarDto(carEntity)

    }
}