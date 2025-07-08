package com.eduardosdl.biosafe.data.repository

import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class UserRepositoryImpl(private val client: HttpClient) : UserRepository {
    override suspend fun getUsers(): List<User> {
        val response = client.get("/users").body<String>()

        val user = Json.decodeFromString<List<User>>(response)

        return user
    }
}