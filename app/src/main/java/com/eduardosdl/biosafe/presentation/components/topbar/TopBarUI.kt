package com.eduardosdl.biosafe.presentation.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.eduardosdl.biosafe.navigation.tabs.TabsItemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUI(
    navController: NavHostController,
    currentTabItem: TabsItemsList,
    actions: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(currentTabItem.labelRes))
        },
        navigationIcon = {
            if (currentTabItem.showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        },
        actions = { actions.invoke() }
    )
}