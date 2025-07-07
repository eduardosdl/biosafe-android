package com.eduardosdl.biosafe.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    @SerialName("fingerprint_id")
    val fingerprintId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("last_access")
    val lastAccess: String? = null
)
