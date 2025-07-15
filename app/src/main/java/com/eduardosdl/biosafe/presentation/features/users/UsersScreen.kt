package com.eduardosdl.biosafe.presentation.features.users

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.presentation.components.shimmerloading.shimmerLoading
import com.eduardosdl.biosafe.presentation.features.tabcontainer.TabScaffoldConfig
import com.eduardosdl.biosafe.presentation.features.util.UiState
import com.eduardosdl.biosafe.presentation.theme.BiosafeTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.time.ExperimentalTime

@Composable
fun UserRoute(
    setScaffoldConfig: (TabScaffoldConfig) -> Unit
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<UsersViewModel>()
    val usersState by viewModel.usersList.collectAsState()
    val createUserState by viewModel.createUser.collectAsState()

    var createUserDialogIsVisible by remember { mutableStateOf(false) }
    var createUserError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(context) {
        setScaffoldConfig(TabScaffoldConfig(actions = {
            IconButton(onClick = { viewModel.fetchUsers() }) {
                Icon(Icons.Default.Refresh, contentDescription = "refresh")
            }
        }, floatButton = {
            createUserDialogIsVisible = true
        }))
    }

    when (usersState) {
        is UiState.Loading -> UserScreen(users = null, isLoading = true)

        is UiState.Success -> {
            val users = (usersState as? UiState.Success)?.data
            if (users.isNullOrEmpty()) {
                EmptyUsers { createUserDialogIsVisible = true }
            } else {
                UserScreen(users = users)
            }
        }

        is UiState.Failure -> {
            Text(stringResource((usersState as UiState.Failure).message))
        }

        else -> {}
    }

    when (createUserState) {
        is CreateUserState.Success -> {
            createUserDialogIsVisible = false
            Toast.makeText(context, "Solicitação de cadastro enviada", Toast.LENGTH_SHORT).show()
        }

        is CreateUserState.ValidationFailure -> {
            createUserError = stringResource((createUserState as? CreateUserState.ValidationFailure)!!.message)
        }

        is CreateUserState.Failure -> {
            Toast.makeText(context, (createUserState as? CreateUserState.Failure)!!.message, Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }

    CreateUserDialog(
        isVisible = createUserDialogIsVisible,
        isSubmitting = createUserState is CreateUserState.Loading,
        error = createUserError,
        onDismiss = { createUserDialogIsVisible = false },
        onConfirm = { viewModel.createUser(it) },
        onResetError = {
            createUserError = null
            viewModel.resetCreateUser()
        }
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
                text = if (hasLastAccess) user.lastAccess else stringResource(R.string.label_without_last_access),
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

@Composable
fun EmptyUsers(openCreateUserDialog: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.label_empty_users),
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = { openCreateUserDialog() }, modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(R.string.label_create_new_user_btn))
        }
    }
}

@Composable
fun CreateUserDialog(
    isVisible: Boolean = false,
    isSubmitting: Boolean = false,
    error: String? = null,
    onResetError: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    val name = remember { mutableStateOf("") }

    LaunchedEffect(isVisible) {
        if (!isVisible) {
            name.value = ""
            onResetError()
        }
    }

    if (!isVisible) return

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = Modifier.padding(16.dp), elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Criar novo usuário", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = name.value,
                    onValueChange = {
                        name.value = it
                        onResetError()
                    },
                    label = { Text("Nome") },
                    supportingText = { error?.let { Text(error) } },
                    isError = error != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        enabled = !isSubmitting
                    ) {
                        Text(text = "Cancelar")
                    }
                    TextButton(
                        onClick = { onConfirm(name.value) }, enabled = !isSubmitting
                    ) {
                        if (isSubmitting) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            return@TextButton
                        }

                        Text(text = "Criar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDialog() {
    BiosafeTheme {
        CreateUserDialog(onDismiss = { }, onConfirm = { }, onResetError = {})
    }
}
