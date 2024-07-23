package com.carenation.car.adapter.out.persistence

import com.carenation.car.adapter.out.repository.CarCategoryRepository
import com.carenation.car.adapter.out.repository.CarRepository
import com.carenation.car.port.out.CarDeleteOutPort
import org.springframework.stereotype.Component


/**
 * CarDeleteOutPort의 구현체
 */
@Component
class CarDeleteAdapter(
    private val carRepository: CarRepository,
    private val carCategoryRepository: CarCategoryRepository,
) : CarDeleteOutPort{

    /**
     * 자동차 삭제
     * @param carId
     */
    override fun delete(carId: Long) {
        // CarCategoryRepository에서 자동차 id에 해당하는 카테고리 엔티티 먼저 삭제
        carCategoryRepository.deleteByCarId(carId)
        // 이후에 CarRepository에서 자동차 엔티티 삭제
        carRepository.deleteById(carId)
    }
}