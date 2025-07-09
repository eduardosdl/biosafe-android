package com.eduardosdl.biosafe.navigation.bottom

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BottomBarUI(currentDestination: NavDestination?, onItemClick: (BottomAppBarItems) -> Unit) {
    BottomAppBar(
        actions = {
            BottomAppBarItems.items.forEach { item ->
                val isSelect = currentDestination?.hierarchy?.any {
                    it.route == item.route::class.qualifiedName
                } == true

                NavigationBarItem(
                    selected = isSelect,
                    onClick = {
                        onItemClick(item)
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.getLabel())
                    },
                    label = {
                        Text(
                            item.getLabel(),
                            color = if (isSelect)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    )

                )
            }
        }
    )
}