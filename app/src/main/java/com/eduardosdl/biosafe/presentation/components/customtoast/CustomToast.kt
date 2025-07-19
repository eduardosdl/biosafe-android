package com.eduardosdl.biosafe.presentation.components.customtoast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.presentation.theme.BiosafeTheme
import kotlinx.coroutines.delay

@Composable
fun CustomToast(toastData: CustomToastData?, onDismiss: () -> Unit) {
    if (toastData == null) return

    var isVisible by remember(toastData) { mutableStateOf(false) }

    var backgroundColor: Color
    var accentColor: Color
    var icon: ImageVector

    when (toastData.severity) {
        CustomToastSeverity.SUCCESS -> {
            backgroundColor = BiosafeTheme.colors.success
            accentColor = BiosafeTheme.colors.successAccent
            icon = Icons.Rounded.CheckCircle
        }

        CustomToastSeverity.ERROR -> {
            backgroundColor = BiosafeTheme.colors.error
            accentColor = BiosafeTheme.colors.errorAccent
            icon = Icons.Rounded.Close
        }

        CustomToastSeverity.WARNING -> {
            backgroundColor = BiosafeTheme.colors.warning
            accentColor = BiosafeTheme.colors.warningAccent
            icon = Icons.Rounded.Warning
        }

        CustomToastSeverity.INFO -> {
            backgroundColor = BiosafeTheme.colors.info
            accentColor = BiosafeTheme.colors.infoAccent
            icon = Icons.Rounded.Info
        }
    }

    LaunchedEffect(toastData) {
        isVisible = true

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
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it } // entra de cima para baixo ✅
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { -it }
        ) + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .clip(MaterialTheme.shapes.small)
                .background(backgroundColor)
                .clickable(onClick = { isVisible = false }), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(accentColor)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "home",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(20.dp),

                )
            }
            Text(toastData.message, color = Color.White, modifier = Modifier.padding(16.dp))
        }
    }
}
