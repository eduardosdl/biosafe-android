package com.eduardosdl.biosafe.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {

    @Serializable
    data object Home: NavRoutes()

    @Serializable
    data object Users: NavRoutes()
}