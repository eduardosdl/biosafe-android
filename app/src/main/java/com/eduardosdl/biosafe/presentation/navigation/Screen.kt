package com.eduardosdl.biosafe.presentation.navigation

sealed class Screen(val rout: String) {
    object Home: Screen("home_screen")
    object Users: Screen("users_screen")
}