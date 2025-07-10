package com.eduardosdl.biosafe.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.eduardosdl.biosafe.presentation.features.splash.SplashScreen
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabContainer

fun NavGraphBuilder.screensNavGraph(navController: NavHostController) {
    composable<ScreensList.SplashScreen> {
        SplashScreen(
            onSplashFinished = {
                navController.navigate(ScreensList.TabContainer) {
                    popUpTo(ScreensList.SplashScreen) {
                        inclusive = true
                    }

                }
            }
        )
    }

    composable<ScreensList.TabContainer> {
        TabContainer(
            navController = navController
        )
    }
}