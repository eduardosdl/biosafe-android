package com.eduardosdl.biosafe.data.repository

import com.eduardosdl.biosafe.data.api.ApiRequest
import com.eduardosdl.biosafe.data.model.CreateUserRequest
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class UserRepositoryImpl(private val client: HttpClient, private val apiRequest: ApiRequest) :
    UserRepository {
    override suspend fun getAll(): List<User> {
        val response = client.get("/users")

        return apiRequest<List<User>>(response)
    }

    override suspend fun create(name: String) {
        val response = client.post("/users") {
            setBody(CreateUserRequest(name))
        }

        apiRequest<List<Unit>>(response)
    }
}