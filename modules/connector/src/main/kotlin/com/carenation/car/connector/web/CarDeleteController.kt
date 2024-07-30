package com.carenation.car.connector.web

import com.carenation.car.application.port.`in`.usecase.CarDeleteUseCase
import com.carenation.car.connector.apipayload.enum.ResultCode
import com.carenation.car.connector.apipayload.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
@Tag(name = "Car-Delete", description = "자동차 삭제 관련 API입니다")
class CarDeleteController(
    private val carDeleteUseCase: CarDeleteUseCase,
) {
    // 자동차 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "자동차를 삭제하는 API입니다", description = "자동차의 ID를 입력받아 해당하는 자동차를 삭제합니다")
    fun delete(
        @PathVariable @NotNull(message = "Car ID는 필수입니다") id: Long,
    ): BaseResponse<Unit> {
        carDeleteUseCase.delete(id)
        return BaseResponse(
            statusCode = ResultCode.DELETE_SUCCESS.statusCode,
            statusMessage = ResultCode.DELETE_SUCCESS.message,
        )
    }
}
