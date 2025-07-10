package com.eduardosdl.biosafe.presentation.features.tabcontainer

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eduardosdl.biosafe.navigation.tabs.TabsItemsList
import com.eduardosdl.biosafe.navigation.tabs.TabsNavHost
import com.eduardosdl.biosafe.presentation.components.bottombar.BottomBarUI
import com.eduardosdl.biosafe.presentation.components.topbar.LocalTopBarConfig
import com.eduardosdl.biosafe.presentation.components.topbar.TopBarConfig
import com.eduardosdl.biosafe.presentation.components.topbar.TopBarUI

@Composable
fun TabContainer() {
    val tabNavController = rememberNavController()
    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarConfig = remember { mutableStateOf(TopBarConfig()) }


    CompositionLocalProvider(LocalTopBarConfig provides topBarConfig) {
        Scaffold(
            bottomBar = {
                BottomBarUI(
                    tabs = TabsItemsList.ALL,
                    currentRoute = currentRoute,
                    onItemClick = {
                        tabNavController.navigate(it.route) {
                            popUpTo(tabNavController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            },
            topBar = {
                TopBarUI(
                    navController = tabNavController,
                    topBarConfig = topBarConfig.value
                )
            },
        ) { innerPadding ->
            TabsNavHost(
                modifier = Modifier.padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Rtl),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Rtl),
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                navHostController = tabNavController
            )
        }
    }
}