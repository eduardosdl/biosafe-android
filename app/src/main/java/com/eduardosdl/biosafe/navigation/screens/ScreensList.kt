package com.eduardosdl.biosafe.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreensList {
    @Serializable
    data object SplashScreen : ScreensList()
    @Serializable
    data object TabContainer : ScreensList()
}