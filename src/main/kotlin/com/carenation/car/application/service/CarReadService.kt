package com.carenation.car.application.service

import com.carenation.car.application.domain.CarInfoDto
import com.carenation.car.application.domain.CarInfoListInDto
import com.carenation.car.port.`in`.usecase.CarReadUseCase
import com.carenation.car.port.out.CarReadOutPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarReadService (
    private val carReadOutPort: CarReadOutPort
): CarReadUseCase {

    @Transactional
    override fun getById(carId: Long): CarInfoDto {
        return carReadOutPort.getById(carId)
    }

    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return carReadOutPort.getAll()
    }

    @Transactional
    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carReadOutPort.getByCategoryName(categoryName)
    }

    override fun getDynamicQuery(req: CarInfoListInDto): List<CarInfoDto> {
        return  carReadOutPort.getDynamicQuery(req)
    }
}