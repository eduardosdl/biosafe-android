package com.eduardosdl.biosafe.domain.usecase

import com.eduardosdl.biosafe.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getAll() = userRepository.getUsers()
}