package com.eduardosdl.biosafe.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val DarkColorScheme = darkColorScheme()
val LightColorScheme = lightColorScheme()

val LightBiosafeScheme = BiosafeScheme(
    success = BiosafeColors.Green600,
    successAccent = BiosafeColors.Green400,
    error = BiosafeColors.Red600,
    errorAccent = BiosafeColors.Red400,
    warning = BiosafeColors.Yellow500,
    warningAccent = BiosafeColors.Yellow200,
    info = BiosafeColors.Blue600,
    infoAccent = BiosafeColors.Blue400,
)

val DarkBiosafeScheme = BiosafeScheme(
    success = BiosafeColors.Green900,
    successAccent = BiosafeColors.Green700,
    error = BiosafeColors.Red900,
    errorAccent = BiosafeColors.Red700,
    warning = BiosafeColors.Yellow900,
    warningAccent = BiosafeColors.Yellow700,
    info = BiosafeColors.Blue900,
    infoAccent = BiosafeColors.Blue700,
)