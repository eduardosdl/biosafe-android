package com.eduardosdl.biosafe.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.eduardosdl.biosafe.presentation.features.home.HomeScreen
import com.eduardosdl.biosafe.presentation.features.users.UserRoute

fun NavGraphBuilder.tabsContainerNavGraph(navController: NavHostController) {
    composable(TabsItemsList.Home.route) {
        HomeScreen()
    }

    composable(TabsItemsList.Users.route) {
        UserRoute()
    }
}