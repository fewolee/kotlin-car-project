package com.carenation.car.application.service

import com.carenation.car.adapter.`in`.dto.CarInfoDto
import com.carenation.car.adapter.`in`.dto.UpdateCarDto
import com.carenation.car.adapter.`in`.dto.request.CarInfoListRequest
import com.carenation.car.adapter.`in`.dto.request.CreateCarRequest
import com.carenation.car.adapter.out.dto.response.CreatedCarResponse
import com.carenation.car.adapter.out.dto.response.UpdatedCarResponse
import com.carenation.car.port.`in`.usecase.CarUseCase
import com.carenation.car.port.out.CarOutport
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CarService (
    private val carOutport : CarOutport
): CarUseCase{

    override fun create(req: CreateCarRequest): CreatedCarResponse {
       return carOutport.create(req)
    }

    override fun getById(carId: Long): CarInfoDto {
        return carOutport.getById(carId)
    }

    override fun getAll(): List<CarInfoDto> {
        return carOutport.getAll()
    }

    override fun getByCategoryName(categoryName: String): List<CarInfoDto> {
        return carOutport.getByCategoryName(categoryName)
    }

    override fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto> {
        return  carOutport.getDynamicQuery(req)
    }

    override fun update(updateCarDto: UpdateCarDto): UpdatedCarResponse {
        return carOutport.update(updateCarDto)
    }

    override fun delete(carId: Long) {
        return carOutport.delete(carId)
    }
}
