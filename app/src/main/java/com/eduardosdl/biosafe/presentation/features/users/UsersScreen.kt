package com.eduardosdl.biosafe.presentation.features.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.presentation.components.shimmerloading.shimmerLoading
import com.eduardosdl.biosafe.presentation.components.topbar.LocalTopBarConfig
import com.eduardosdl.biosafe.presentation.components.topbar.TopBarConfig
import com.eduardosdl.biosafe.presentation.features.util.UiState
import org.koin.androidx.compose.koinViewModel
import kotlin.time.ExperimentalTime

@Composable
fun UserRoute() {
    val viewModel = koinViewModel<UsersViewModel>()
    val usersState by viewModel.usersList.collectAsState()

    val topBarConfig = LocalTopBarConfig.current

    LaunchedEffect(Unit) {
        topBarConfig.value = TopBarConfig(
            title = { stringResource(R.string.label_users_screen_app_bar) },
            actions = {
                IconButton(onClick = { viewModel.fetchUsers() }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Recarregar")
                }
            },
        )
    }

    UserScreen(
        users = (usersState as? UiState.Success)?.data, isLoading = usersState is UiState.Loading
    )
}

@Composable
fun UserScreen(users: List<User>?, isLoading: Boolean = false) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), userScrollEnabled = !isLoading
    ) {
        if (isLoading) {
            items(4) {
                LoadingItem()
            }
        } else {
            items(users.orEmpty()) { user ->
                UserItem(user)
            }
        }

    }

}

@OptIn(ExperimentalTime::class)
@Composable
fun UserItem(user: User) {
    val hasLastAccess = user.lastAccess != null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(user.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "Id da digital: ${user.fingerprintId}",
            )
            Text(
                text = if (hasLastAccess) user.lastAccess else "Nenhum acesso registrado",
                color = if (hasLastAccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmerLoading()
    )
}
