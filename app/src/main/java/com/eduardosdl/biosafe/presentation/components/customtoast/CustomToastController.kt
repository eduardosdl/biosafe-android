package com.eduardosdl.biosafe.presentation.components.customtoast

import androidx.compose.runtime.mutableStateOf

class CustomToastController {
    private val _toastData = mutableStateOf<CustomToastData?>(null)
    val toastData: CustomToastData? get() = _toastData.value

    val defaultDuration = 3000L

    fun showSuccess(message: String, duration: Long = defaultDuration) {
        _toastData.value = CustomToastData(message, CustomToastSeverity.SUCCESS, duration)
    }

    fun showError(message: String, duration: Long = defaultDuration) {
        _toastData.value = CustomToastData(message, CustomToastSeverity.ERROR, duration)
    }

    fun showWanning(message: String, duration: Long = defaultDuration) {
        _toastData.value = CustomToastData(message, CustomToastSeverity.WARNING, duration)
    }

    fun showInfo(message: String, duration: Long = defaultDuration) {
        _toastData.value = CustomToastData(message, CustomToastSeverity.INFO, duration)
    }

    fun onDismiss() {
        _toastData.value = null
    }
}