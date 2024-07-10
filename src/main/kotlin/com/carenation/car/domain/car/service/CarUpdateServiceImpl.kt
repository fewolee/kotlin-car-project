package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.UpdateCarDto
import com.carenation.car.domain.car.dto.UpdatedCarDto
import com.carenation.car.domain.car.entity.CarEntity
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
    private val carCategoryRepository: CarCategoryRepository
) : CarUpdateService {

    @Transactional
    override fun update(updateCarDto: UpdateCarDto): UpdatedCarDto {
        // carEntity 수정
        val updatedCarEntity = updateCarEntity(updateCarDto)

        return UpdatedCarDto(
            modelName = updatedCarEntity.modelName,
            manufacture = updatedCarEntity.manufacture,
            productionYear = updatedCarEntity.productionYear,
            rentAvailable = updatedCarEntity.rentAvailable,
            categoryNames = updateCarCategoryEntity(updateCarDto, updatedCarEntity)
        )
    }

    // carEntity 업데이트
    private fun updateCarEntity(updateCarDto: UpdateCarDto): CarEntity {
        val carEntity = carRepository.findById(updateCarDto.id)
            .orElseThrow { IllegalArgumentException("해당하는 자동차 ID가 없습니다: ${updateCarDto.id}") }
        carEntity.modelName = updateCarDto.modelName
        carEntity.manufacture = updateCarDto.manufacture
        carEntity.rentAvailable = updateCarDto.rentAvailable
        carEntity.productionYear = updateCarDto.productionYear
        return carRepository.save(carEntity)
    }


    // carCategoryEntity 업데이트
    private fun updateCarCategoryEntity(updateCarDto: UpdateCarDto, updatedCarEntity: CarEntity): List<String> {
        val categoryNames = mutableListOf<String>()
        carCategoryRepository.deleteByCarEntity(updatedCarEntity)


        updateCarDto.categoryNames.forEach() { categoryName ->
            val category = categoryRepository.findByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = updatedCarEntity, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
            categoryNames.add(categoryName)
        }
        return categoryNames
    }


}

