package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.car.request.CarInfoListRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarReadServiceImpl(
    private val carRepository: CarRepository,
) : CarReadService {

    @Transactional
    override fun getById(carId: Long): CarInfoDto {
        return carRepository.getById(carId)
    }

    // 모든 자동차 조회
    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carRepository.getAll()
    }

    //카테고리 이름으로 자동차 조회
    @Transactional
    override fun getByCategoryName(category: String): List<CarInfoDto> {
        return carRepository.getByCategoryName(category)
    }


    @Transactional
    override fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto> {
        return carRepository.getDynamicQuery(req)
    }


}