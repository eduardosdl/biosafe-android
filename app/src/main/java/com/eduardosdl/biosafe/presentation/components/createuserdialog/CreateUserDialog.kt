package com.eduardosdl.biosafe.presentation.components.createuserdialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CreateUserDialog(
    isVisible: Boolean,
    isSubmitting: Boolean,
    error: String?,
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