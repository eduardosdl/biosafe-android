package com.eduardosdl.biosafe.navigation.top

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUI(
    navController: NavHostController,
    topBarConfig: TopBarConfig,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = topBarConfig.title())
        },
        navigationIcon = {
            if (topBarConfig.showBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        },
        actions = { topBarConfig.actions?.invoke() }
    )
}