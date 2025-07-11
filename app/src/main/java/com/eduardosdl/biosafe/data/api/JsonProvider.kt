package com.eduardosdl.biosafe.data.api

import kotlinx.serialization.json.Json

class JsonProvider {
    val instance = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

}