package com.eduardosdl.biosafe.data.api

import com.eduardosdl.biosafe.domain.enum.ResultStatus
import com.eduardosdl.biosafe.domain.model.BaseApiResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class ApiRequest(val json: Json) {
    suspend inline operator fun <reified T : Any> invoke(
        response: HttpResponse,
        isPaginated: Boolean = true
    ): BaseApiResponse<T> {
        return try {
            if (response.status.value in 200..299) {
                val responseBody = response.body<String>()
                val baseResponse = json.decodeFromString<T>(responseBody)

                return BaseApiResponse(
                    results = baseResponse,
                    resultStatus = ResultStatus.SUCCESS
                )
            } else {
                val errorBody = response.body<String>()
                val errorResponse = json.decodeFromString<BaseApiResponse<Unit>>(errorBody)

                if (response.status >= HttpStatusCode.InternalServerError) {
                    BaseApiResponse(
                        results = null,
                        statusCode = response.status.value,
                        resultStatus = ResultStatus.ERROR,
                    )
                } else {
                    BaseApiResponse(
                        results = null,
                        statusCode = response.status.value,
                        resultStatus = ResultStatus.ERROR,
                        message = errorResponse.message
                    )
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            BaseApiResponse(
                results = null,
                statusCode = response.status.value,
                resultStatus = ResultStatus.ERROR,
            )
        }
    }
}