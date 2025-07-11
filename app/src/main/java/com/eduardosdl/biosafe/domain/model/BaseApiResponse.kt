package com.eduardosdl.biosafe.domain.model

import com.eduardosdl.biosafe.domain.enum.ResultStatus

data class BaseApiResponse<T>(
    val resultStatus: ResultStatus = ResultStatus.UNKNOWN,
    val statusCode: Int? = null,
    val message: String? = null,
    val results: T? = null
)