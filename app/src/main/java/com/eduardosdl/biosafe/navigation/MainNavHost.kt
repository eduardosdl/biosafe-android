package com.eduardosdl.biosafe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eduardosdl.biosafe.presentation.home.HomeScreen
import com.eduardosdl.biosafe.presentation.users.UsersScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Home,
        modifier = modifier
    ) {
        composable<NavRoutes.Home> {
            HomeScreen()
        }

        composable<NavRoutes.Users> {
            UsersScreen()
        }
    }
}