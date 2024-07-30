package com.carenation.car.application.port.out

import com.carenation.car.application.dto.CarInfoListInDto
import com.carenation.car.domain.CarModel
import org.springframework.stereotype.Repository

/**
 * out port
 */
@Repository
interface CarReadRepository {
    // 자동차의 id로 자동차 엔티티를 조회해 CarModel 반환
    fun getById(carId: Long): CarModel

    // 자동차의 모든 엔티티를 조회해 CarModel 리스트로 반환
    fun getAll(): List<CarModel>

    // 카테고리 이름으로 자동차 엔티티를 조회해 CarModel 리스트로 반환
    fun getByCategoryName(categoryName: String): List<CarModel>

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarModel 리스트로 반환
    fun getDynamicQuery(req: CarInfoListInDto): List<CarModel>
}
