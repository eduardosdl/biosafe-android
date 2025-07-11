package com.eduardosdl.biosafe.domain.repository

import com.eduardosdl.biosafe.domain.model.BaseApiResponse
import com.eduardosdl.biosafe.domain.model.User

interface UserRepository {
    suspend fun getUsers(): BaseApiResponse<List<User>>
}