package com.eduardosdl.biosafe.data.api

import com.eduardosdl.biosafe.data.model.ExceptionResponse
import com.eduardosdl.biosafe.domain.exceptions.RequestException
import com.eduardosdl.biosafe.domain.exceptions.RequestExceptionType
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json

class ApiRequest(val json: Json) {
    suspend inline operator fun <reified T> invoke(
        response: HttpResponse, isPaginated: Boolean = true
    ): T {
        val statusCode = response.status

        if (statusCode.value in 200..299) {
            val responseBody = response.body<String>()
            return json.decodeFromString<T>(responseBody)
        }

        if (statusCode.value in 400..499) {
            throw RequestException(
                RequestExceptionType.CLIENT,
                response.body<ExceptionResponse>().message
            )
        }

        throw RequestException(
            RequestExceptionType.SERVER,
            response.body<String>()
        )
    }
}
