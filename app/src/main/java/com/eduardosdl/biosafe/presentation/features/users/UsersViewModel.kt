package com.eduardosdl.biosafe.presentation.features.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            Log.d("UserRepositoryImpl", "fetchUsers: called")
            try {
                _users.value = userRepository.getUsers()
            } catch (e: Exception) {
                Log.d("UserRepositoryImpl", "fetchUsers: error $e")
                _users.value = emptyList()
            }
        }
    }
}