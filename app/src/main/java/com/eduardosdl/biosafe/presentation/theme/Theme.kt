package com.eduardosdl.biosafe.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalBiosafeColors = staticCompositionLocalOf {
    LightBiosafeScheme
}

@Composable
fun BiosafeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val biosafeScheme = if (darkTheme) DarkBiosafeScheme else LightBiosafeScheme

    CompositionLocalProvider(LocalBiosafeColors provides biosafeScheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object BiosafeTheme {
    val colors: BiosafeScheme
        @Composable get() = LocalBiosafeColors.current
}