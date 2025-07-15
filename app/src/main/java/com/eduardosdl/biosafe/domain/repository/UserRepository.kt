package com.eduardosdl.biosafe.domain.repository

import com.eduardosdl.biosafe.domain.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun create(name: String)
}