package com.eduardosdl.biosafe.presentation.features.util

import androidx.annotation.StringRes

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Failure(@param:StringRes val message: Int) : UiState<Nothing>()
}