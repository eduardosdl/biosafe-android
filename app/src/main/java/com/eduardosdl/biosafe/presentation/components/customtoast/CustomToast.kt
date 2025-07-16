package com.eduardosdl.biosafe.presentation.components.customtoast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CustomToast(toastData: CustomToastData?, onDismiss: () -> Unit) {
    if (toastData == null) return

    var isVisible by remember(toastData) { mutableStateOf(true) }

    val (backgroundColor, textColor) = when (toastData.severity) {
        CustomToastSeverity.SUCCESS -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
        CustomToastSeverity.ERROR -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
        CustomToastSeverity.WARNING -> MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer
        CustomToastSeverity.INFO -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant
    }

    LaunchedEffect(toastData) {
        delay(toastData.duration)

        isVisible = false
    }

    LaunchedEffect(isVisible) {
        if (!isVisible) {
            delay(300)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = isVisible, enter = fadeIn(), exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 16.dp)
                .background(backgroundColor)
                .clickable(onClick = onDismiss),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(toastData.message, color = textColor, modifier = Modifier.padding(16.dp))
        }
    }
}
