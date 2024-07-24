package com.carenation.car.adapter.out

import com.carenation.car.adapter.out.repository.CarRepository
import com.carenation.car.adapter.out.repository.CategoryRepository
import com.carenation.car.application.domain.CarModel
import com.carenation.car.dto.CarInfoListInDto
import com.carenation.car.port.out.CarReadOutPort
import org.springframework.stereotype.Component

/**
 * CarReadOutPort의 구현체
 */
@Component
class CarReadAdapter(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository,
) : CarReadOutPort {
    /**
     * 자동차 Id로 자동차 정보 조회
     * @param carId
     * @return CarModel
     */
    override fun getById(carId: Long): CarModel = carRepository.getById(carId)

    /**
     * 모든 자동차 정보 조회
     * @return List<CarModel>
     */
    override fun getAll(): List<CarModel> = carRepository.getAll()

    /**
     * 카테고리 이름으로 자동차 정보 조회
     * @param categoryName
     * @return List<CarModel>
     */
    override fun getByCategoryName(categoryName: String): List<CarModel> = carRepository.getByCategoryName(categoryName)

    /**
     * 모델명, 제조일자, 생산년도로 자동차 정보를 동적으로 조회
     * @param CarInfoListinDto
     * @return List<CarModel>
     */
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarModel> = carRepository.getDynamicQuery(req)

    override fun existsByCarId(carId: Long): Boolean = carRepository.existsById(carId)

    override fun existsByCategory(category: String): Boolean = categoryRepository.findByCategoryName(category) != null
}
