package com.carenation.car.adapter.out.persistence

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.adapter.out.persistence.repository.CarRepository
import com.carenation.car.application.domain.CarInfoListInDto
import com.carenation.car.port.out.CarReadOutPort
import org.springframework.stereotype.Component

/*
CarReadOutPort의 구현체
 */
@Component
class CarReadAdapter(

    private val carRepository: CarRepository,
) :  CarReadOutPort{

    // 자동차 id로 자동차 정보 조회
    override fun getById(carId: Long): CarInfoDto {
        return carRepository.getById(carId)
    }

    // 모든 자동차 정보 조회
    override fun getAll(): List<CarInfoDto> {
        return carRepository.getAll()
    }

    // 카테고리 이름으로 자동차 정보 조회
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carRepository.getByCategoryName(categoryName)
    }

    // 모델명, 제조일자, 생산년도로 자동차 정보를 동적으로 조회
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarInfoDto> {
        return carRepository.getDynamicQuery(req)
    }


}