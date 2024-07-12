package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarResponse
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.mapper.CarMapper
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.CarCategoryEntity
import com.carenation.car.domain.category.repository.CarCategoryRepository
import com.carenation.car.domain.category.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CarUpdateServiceImpl(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val carMapper : CarMapper
) : CarUpdateService {

    @Transactional
    override fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse {
        // carEntity 수정
        val updatedCar = updateCarEntity(updateCarDto)

        return carMapper.toUpdatedCarDto(updatedCar,updateCarCategoryEntity(updateCarDto, updatedCar))

    }

    // carEntity 업데이트
    private fun updateCarEntity(updateCarDto: UpdateCarDto): CarEntity {

        val car = carRepository.findById(updateCarDto.id)
            .orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: ${updateCarDto.id}") }

        car.updateInfo(modelName = updateCarDto.modelName,
                        manufacture = updateCarDto.manufacture,
                        productionYear = updateCarDto.productionYear,
                         rentAvailable = updateCarDto.rentAvailable)

        return carRepository.save(car)
    }


    // carCategoryEntity 업데이트
    private fun updateCarCategoryEntity(updateCarDto: UpdateCarDto, updatedCar: CarEntity): List<String> {
        val categoryNames = mutableListOf<String>()
        carCategoryRepository.deleteByCarId(updateCarDto.id)

        updateCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = updatedCar, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }
        return categoryNames
    }


}

