package com.carenation.car.connector.web

import com.carenation.car.application.port.`in`.usecase.CarReadUseCase
import com.carenation.car.connector.annotation.NotExistsCarId
import com.carenation.car.connector.annotation.NotExistsCategory
import com.carenation.car.connector.apipayload.response.BaseResponse
import com.carenation.car.connector.dto.request.CarInfoListRequest
import com.carenation.car.connector.mapper.CarInMapper
import com.carenation.car.domain.CarModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
@Tag(name = "Car-Read", description = "자동차 조회 관련 API입니다")
class CarReadController(
    private val carReadUseCase: CarReadUseCase,
    private val carInMapper: CarInMapper,
) {
    // 자동차 id로 자동차 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "자동차의 ID로 단건 조회하는 API입니다", description = "자동차의 ID를 입력받아 해당하는 자동차의 정보를 조회합니다")
    fun getByCustomId(
        @PathVariable @NotExistsCarId id: Long,
    ): BaseResponse<CarModel> = BaseResponse(data = carReadUseCase.getById(id))

    // 모든 자동차 정보 조회
    @GetMapping
    @Operation(summary = "모든 자동차를 조회하는 API입니다", description = "모든 자동차의 정보들을 조회합니다")
    fun getAll(): BaseResponse<List<CarModel>> = BaseResponse(data = carReadUseCase.getAll())

    // 카테고리로 자동차 정보 조회
    @GetMapping("/category/{category}")
    @Operation(summary = "카테고리로 자동차를 조회합니다", description = "카테고리를 입력받아 해당 카테고리의 모든 자동차를 조회합니다")
    fun getByCategory(
        @PathVariable @NotExistsCategory category: String,
    ): BaseResponse<List<CarModel>> = BaseResponse(data = carReadUseCase.getByCategoryName(category))

    // 제조사, 모델명, 생산년도로 자동차 정보 조회
    @GetMapping("/")
    @Operation(summary = "자동차의 정보들로 단건 조회하는 API입니다", description = "자동차의 제조사,모델명,생산년도로 자동차 정보를 조회합니다")
    fun getByDynamicQuery(
        @Validated @ModelAttribute req: CarInfoListRequest,
    ): BaseResponse<List<CarModel>> = BaseResponse(data = carReadUseCase.getDynamicQuery(carInMapper.toCarInfoListInDto(req)))
}
