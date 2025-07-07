package com.eduardosdl.biosafe.data.repository

import android.util.Log
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class UserRepositoryImpl(private val client: HttpClient) : UserRepository {
    private val tag = "UserRepositoryImpl"

    override suspend fun getUsers(): List<User> {
        val response = client.get("http://192.168.1.13:1880/users").body<String>()

        Log.d(tag, "getUsers: $response")
        val user = Json.decodeFromString<List<User>>(response)




        return user
    }
}