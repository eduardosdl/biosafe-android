package com.eduardosdl.biosafe.data.api

import com.eduardosdl.biosafe.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(private val json: Json) {
    val instance = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = BuildConfig.API_URL
                contentType(ContentType.Application.Json)
            }
        }
    }
}