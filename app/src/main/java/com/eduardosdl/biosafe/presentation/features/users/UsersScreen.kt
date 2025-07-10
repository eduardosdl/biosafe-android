package com.eduardosdl.biosafe.presentation.features.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.presentation.components.topbar.LocalTopBarConfig
import com.eduardosdl.biosafe.presentation.components.topbar.TopBarConfig
import com.eduardosdl.biosafe.presentation.features.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UsersScreen(viewModel: HomeViewModel = koinViewModel()) {
    val users by viewModel.users.collectAsState()

    val topBarConfig = LocalTopBarConfig.current

    LaunchedEffect(Unit) {
        topBarConfig.value = TopBarConfig(
            title = { stringResource(R.string.label_users_screen_app_bar) },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Recarregar")
                }
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Usuários Cadastrados", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Nome: ${user.name}")
            Text("ID da Digital: ${user.fingerprintId}")
            Text("Último Acesso: ${user.lastAccess}")
        }
    }
}