package com.eduardosdl.biosafe.domain.exceptions

sealed class AppException {
    object NetworkError : AppException()
    object ServerError : AppException()
    data class ValidationError(val reason: ValidationExceptionType) : AppException()
    data class UnknownError(val message: String? = null) : AppException()
}
