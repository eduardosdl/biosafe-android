package com.eduardosdl.biosafe.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.eduardosdl.biosafe.R

sealed class BottomAppBarItems(
    val route: NavRoutes,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
) {
    data object Home : BottomAppBarItems(
        route = NavRoutes.Home,
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )

    data object Users : BottomAppBarItems(
        route = NavRoutes.Users,
        icon = Icons.Default.Person,
        selectedIcon = Icons.Default.Person
    )

    @Composable
    fun getLabel(): String {
        return when (this) {
            is Home -> stringResource(R.string.label_home_screen_app_bar)
            is Users -> stringResource(R.string.label_users_screen_app_bar)
        }
    }

    companion object {
        val items = listOf(Home, Users)
    }
}