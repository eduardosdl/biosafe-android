package com.eduardosdl.biosafe.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(private val name: String)
