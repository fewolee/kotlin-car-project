package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.car.repository.CarCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarDeleteServiceImpl(
    private val carRepository: CarRepository,
    private val carCategoryRepository: CarCategoryRepository
): CarDeleteService{

    // 자동차 id로 엔티티 삭제
    @Transactional
    override fun delete(carId: Long) {

        // CarCategoryRepository에서 자동차 id에 해당하는 엔티티 먼저 삭제
        carCategoryRepository.deleteByCarId(carId)
        // 이후에 CarRepository에서 자동차 엔티티 삭제
        carRepository.deleteById(carId)
    }
}