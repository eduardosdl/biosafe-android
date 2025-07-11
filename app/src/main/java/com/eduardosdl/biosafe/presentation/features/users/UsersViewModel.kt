package com.eduardosdl.biosafe.presentation.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardosdl.biosafe.domain.enum.ResultStatus
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.usecase.UserUseCase
import com.eduardosdl.biosafe.presentation.features.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _usersList = MutableStateFlow<UiState<List<User>>>(UiState.Idle)
    val usersList = _usersList

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _usersList.value = UiState.Loading

            val response = userUseCase.getAll()

            when (response.resultStatus) {
                ResultStatus.SUCCESS -> {
                    val userUi = UserUiMapper.fromDomainList(response.results ?: emptyList())
                    _usersList.value = UiState.Success(userUi)
                }

                else -> {}
            }
        }
    }
}