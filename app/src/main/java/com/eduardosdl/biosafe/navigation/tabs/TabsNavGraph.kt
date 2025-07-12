package com.eduardosdl.biosafe.navigation.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.eduardosdl.biosafe.presentation.features.home.HomeScreen
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabScaffoldConfig
import com.eduardosdl.biosafe.presentation.features.users.UserRoute

fun NavGraphBuilder.tabsContainerNavGraph(
    navController: NavHostController, setScaffoldConfig: (TabScaffoldConfig) -> Unit
) {
    composable(TabsItemsList.Home.route) {
        HomeScreen {
            setScaffoldConfig(it)
        }
    }

    composable(TabsItemsList.Users.route) {
        UserRoute {
            setScaffoldConfig(it)
        }
    }
}