package com.eduardosdl.biosafe.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun TabsNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = TabsItemsList.Home.route
    ) {
        tabsContainerNavGraph(navHostController)
    }
}