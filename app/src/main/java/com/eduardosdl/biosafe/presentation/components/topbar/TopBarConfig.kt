package com.eduardosdl.biosafe.presentation.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf

data class TopBarConfig(
    val title: @Composable () -> String = { "" },
    val actions: @Composable (() -> Unit)? = null,
    val showBack: Boolean = true,
)

val LocalTopBarConfig = compositionLocalOf<MutableState<TopBarConfig>> {
    error("No TopBarConfig provided")
}