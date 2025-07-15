package com.eduardosdl.biosafe.presentation.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.domain.exceptions.ValidationExceptionType
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.usecase.UserUseCase
import com.eduardosdl.biosafe.domain.utils.AppResult
import com.eduardosdl.biosafe.presentation.features.util.UiState
import com.eduardosdl.biosafe.presentation.features.util.UserUiMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _usersList = MutableStateFlow<UiState<List<User>>>(UiState.Idle)
    val usersList = _usersList

    private val _createUser = MutableStateFlow<CreateUserState>(CreateUserState.Idle)
    val createUser = _createUser


    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _usersList.value = UiState.Loading

            val response = userUseCase.getAll()

            when (response) {
                is AppResult.Success -> {
                    val userUi = UserUiMapper.fromDomainList(response.data)
                    _usersList.value = UiState.Success(userUi)
                }

                is AppResult.Failure -> {
                    _usersList.value = UiState.Failure(response.error)
                }

                else -> {}
            }
        }
    }

    fun createUser(name: String) {
        viewModelScope.launch {
            _createUser.value = CreateUserState.Loading

            delay(1000)

            val response = userUseCase.create(name)

            _createUser.value = when (response) {
                is AppResult.Success -> CreateUserState.Success

                is AppResult.Failure -> CreateUserState.Failure(response.error)

                is AppResult.ValidationFailure -> CreateUserState.ValidationFailure(
                    getValidationError(
                        response.type
                    )
                )
            }
        }
    }

    private fun getValidationError(validationType: ValidationExceptionType): Int {
        return when (validationType) {
            ValidationExceptionType.EMPTY -> R.string.create_user_field_empty
            ValidationExceptionType.ALREADY_EXISTS -> R.string.create_user_already_exists
        }
    }

    fun resetCreateUser() {
        _createUser.value = CreateUserState.Idle
    }
}