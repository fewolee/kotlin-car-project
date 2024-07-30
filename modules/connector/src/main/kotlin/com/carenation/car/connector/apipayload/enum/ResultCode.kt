package com.carenation.car.connector.apipayload.enum

import org.springframework.http.HttpStatus

enum class ResultCode(
    val statusCode: Int,
    val message: String,
) {
    SUCCESS(HttpStatus.OK.value(), "성공"), // 200
    CREATE_SUCCESS(HttpStatus.CREATED.value(), "성공적으로 생성되었습니다"), // 201
    DELETE_SUCCESS(HttpStatus.OK.value(), "성공적으로 삭제되었습니다"), // 200
    UPDATE_SUCCESS(HttpStatus.OK.value(), "성공적으로 수정되었습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 에러가 발생했습니다"), // 500
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "항목이 올바르지 않습니다"), // 400
}
