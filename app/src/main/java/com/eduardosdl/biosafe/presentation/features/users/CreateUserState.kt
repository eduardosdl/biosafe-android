package com.eduardosdl.biosafe.presentation.features.users

import androidx.annotation.StringRes

sealed class CreateUserState {
    object Idle : CreateUserState()
    object Loading : CreateUserState()
    object Success : CreateUserState()
    data class Failure(@param:StringRes val message: Int) : CreateUserState()
    data class ValidationFailure(@param:StringRes val message: Int) : CreateUserState()
}