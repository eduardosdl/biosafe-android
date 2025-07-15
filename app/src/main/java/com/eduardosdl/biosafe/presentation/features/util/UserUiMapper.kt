package com.eduardosdl.biosafe.presentation.features.util

import com.eduardosdl.biosafe.domain.model.User
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object UserUiMapper {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    fun fromDomain(user: User): User = User(
        id = user.id,
        name = user.name,
        fingerprintId = user.fingerprintId,
        createdAt = formatIsoDate(user.createdAt),
        lastAccess = user.lastAccess?.let { formatIsoDate(it) }
    )

    fun fromDomainList(users: List<User>): List<User> =
        users.map { fromDomain(it) }

    private fun formatIsoDate(iso: String): String {
        val parsed = ZonedDateTime.parse(iso)
        return parsed.format(formatter)
    }
}