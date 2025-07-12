package com.eduardosdl.biosafe.navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabScaffoldConfig

@Composable
fun TabsNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    setScaffoldConfig: (TabScaffoldConfig) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = TabsItemsList.Home.route
    ) {
        tabsContainerNavGraph(navHostController, setScaffoldConfig)
    }
}