package com.eduardosdl.biosafe.presentation.features.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabScaffoldConfig

@Composable
fun HomeScreen(
    setScaffoldConfig: (TabScaffoldConfig) -> Unit
) {
    setScaffoldConfig(
        TabScaffoldConfig(
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Share, contentDescription = "Conexões")
                }
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.Refresh, contentDescription = "refresh")
                }
            }
        )
    )

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