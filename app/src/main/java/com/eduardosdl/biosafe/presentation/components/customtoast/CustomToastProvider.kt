package com.eduardosdl.biosafe.presentation.components.customtoast

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun CustomToastProvider(content: @Composable () -> Unit) {
    val controller = remember { CustomToastController() }

    Box {
        CompositionLocalProvider(LocalToastController provides controller) {
            content()
        }

        CustomToast(controller.toastData, onDismiss = { controller.onDismiss() })
    }

}

val LocalToastController = staticCompositionLocalOf<CustomToastController> {
    error("No CustomToastController provided")
}