package com.eduardosdl.biosafe.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eduardosdl.biosafe.navigation.BottomBarUI
import com.eduardosdl.biosafe.navigation.MainNavHost
import com.eduardosdl.biosafe.presentation.splash.SplashScreen

@Composable
fun AppScreen() {
    var isSplashFinished by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (isSplashFinished) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {
                BottomBarUI(
                    currentDestination = currentDestination,
                    onItemClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        ) { innerPadding ->
            MainNavHost(
                modifier = Modifier.padding(innerPadding),
                navHostController = navController,
            )
        }
    } else {
        SplashScreen(onSplashFinished = { isSplashFinished = true })
    }
}