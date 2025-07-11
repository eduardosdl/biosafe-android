package com.eduardosdl.biosafe.data.repository

import com.eduardosdl.biosafe.data.api.ApiRequest
import com.eduardosdl.biosafe.domain.enum.ResultStatus
import com.eduardosdl.biosafe.domain.model.BaseApiResponse
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class UserRepositoryImpl(private val client: HttpClient, private val apiRequest: ApiRequest) :
    UserRepository {
    override suspend fun getUsers(): BaseApiResponse<List<User>> {
        return try {
            val response = client.get("/users")

            apiRequest<List<User>>(response)
        } catch (e: Exception) {
            e.printStackTrace()
            BaseApiResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }
}