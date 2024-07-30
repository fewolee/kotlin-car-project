package com.carenation.car.connector.web

import com.carenation.car.application.port.`in`.usecase.CarCreateUseCase
import com.carenation.car.connector.apipayload.enum.ResultCode
import com.carenation.car.connector.apipayload.response.BaseResponse
import com.carenation.car.connector.dto.request.CarCreateRequest
import com.carenation.car.connector.mapper.CarInMapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
// @ComponentScan(basePackages = ["com.carenation.application.car"])
@RequestMapping("/cars")
@Tag(name = "Car-Create", description = "자동차 생성 관련 API입니다")
class CarCreateController(
    private val carCreateUseCase: CarCreateUseCase,
    private val carInMapper: CarInMapper,
) {
    // 자동차 등록
    @PostMapping
    @Operation(summary = "자동차를 생성하는 API입니다", description = "자동차의 정보들을 입력받아 자동차를 생성합니다")
    fun create(
        @Validated @RequestBody req: CarCreateRequest,
    ): BaseResponse<Unit> {
        carCreateUseCase.create(carInMapper.toCarCreateInDto(req))
        return BaseResponse(
            statusCode = ResultCode.CREATE_SUCCESS.statusCode,
            statusMessage = ResultCode.CREATE_SUCCESS.message,
        )
    }
}
