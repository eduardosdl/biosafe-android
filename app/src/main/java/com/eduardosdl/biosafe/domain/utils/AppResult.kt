package com.eduardosdl.biosafe.domain.utils

import androidx.annotation.StringRes
import com.eduardosdl.biosafe.domain.exceptions.ValidationExceptionType

sealed class AppResult<out T> {
    data class Success<out T>(val data: T): AppResult<T>()
    data class Failure(@param:StringRes val error: Int): AppResult<Nothing>()
    data class ValidationFailure(val type: ValidationExceptionType) : AppResult<Nothing>()
}