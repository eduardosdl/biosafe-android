package com.eduardosdl.biosafe.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.navigation.top.LocalTopBarConfig
import com.eduardosdl.biosafe.navigation.top.TopBarConfig

@Composable
fun HomeScreen() {
    val topBarConfig = LocalTopBarConfig.current

    LaunchedEffect(Unit) {
        topBarConfig.value = TopBarConfig(
            title = { stringResource(R.string.label_home_screen_app_bar) },
            showBack = false,
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Share, contentDescription = "Conexões")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Recarregar")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Home", style = MaterialTheme.typography.headlineSmall)
    }
}