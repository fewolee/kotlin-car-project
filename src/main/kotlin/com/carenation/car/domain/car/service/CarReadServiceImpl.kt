package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.car.dto.request.CarInfoListRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarReadServiceImpl(
    private val carRepository: CarRepository,
) : CarReadService {

    // 자동차의 id로 자동차 엔티티를 조회해 CarInfoDto로 반환
    @Transactional
    override fun getById(carId: Long): CarInfoDto {
        return carRepository.getById(carId)
    }

    // 자동차의 모든 엔티티를 조회해 CarInfoDto 리스트로 반환
    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carRepository.getAll()
    }

    // 카테고리 이름으로 자동차 엔티티를 조회해 CarInfDto 리스트로 반환
    @Transactional
    override fun getByCategoryName(category: String): List<CarInfoDto> {
        return carRepository.getByCategoryName(category)
    }

    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarInfoDto 리스트로 반환
    @Transactional
    override fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto> {
        return carRepository.getDynamicQuery(req)
    }


}