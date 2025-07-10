package com.eduardosdl.biosafe.navigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreensNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreensList.SplashScreen
    ) {
        screensNavGraph(navController)
    }
}