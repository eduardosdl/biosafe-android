package com.eduardosdl.biosafe.presentation.components.bottombar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eduardosdl.biosafe.navigation.tabs.TabsItemsList

@Composable
fun BottomBarUI(
    tabs: List<TabsItemsList>,
    currentRoute: String?,
    onItemClick: (TabsItemsList) -> Unit,
) {
    BottomAppBar(
        actions = {
            tabs.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemClick(item) },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = stringResource(item.labelRes))
                    },
                    label = {
                        Text(
                            text = stringResource(item.labelRes),
                            color = if (isSelected)
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