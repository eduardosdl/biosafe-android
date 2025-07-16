package com.eduardosdl.biosafe.presentation.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.presentation.components.customtoast.LocalToastController
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabScaffoldConfig

@Composable
fun HomeScreen(
    setScaffoldConfig: (TabScaffoldConfig) -> Unit
) {
    val toast = LocalToastController.current

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
        Button(
            onClick = {
                toast.showSuccess("Mensagem de sucesso")
            }
        ) {
            Text("Sucesso")
        }
        Button(
            onClick = {
                toast.showError("Mensagem de sucesso")
            }
        ) {
            Text("Erro")
        }
        Button(
            onClick = {
                toast.showWanning("Mensagem de sucesso")
            }
        ) {
            Text("Warnning")
        }
        Button (onClick = {
            toast.showInfo("Mensagem de sucesso")
        }) {
            Text("Info")
        }
    }
}