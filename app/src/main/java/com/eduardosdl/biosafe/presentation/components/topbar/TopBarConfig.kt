package com.eduardosdl.biosafe.presentation.components.topbar

import androidx.compose.runtime.Composable

data class TopBarConfig(
    val title: @Composable () -> String = { "" },
    val actions: @Composable (() -> Unit)? = null,
    val showBack: Boolean = true,
)
