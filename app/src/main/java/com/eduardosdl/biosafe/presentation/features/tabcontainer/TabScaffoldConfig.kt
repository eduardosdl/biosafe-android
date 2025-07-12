package com.eduardosdl.biosafe.presentation.features.tabcontainer

import androidx.compose.runtime.Composable

data class TabScaffoldConfig(
    val actions: @Composable (() -> Unit)? = null,
    val floatButton: (() -> Unit)? = null
)