package com.carenation.car.apipayload.response

import com.carenation.car.apipayload.enum.ResultCode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class BaseResponse<T>(
    val statusCode: Int = ResultCode.SUCCESS.statusCode,
    val statusMessage: String? = ResultCode.SUCCESS.message,
    val responseTime: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    val data: T? = null,
)
