package com.eduardosdl.biosafe.presentation.components.customtoast

data class CustomToastData(
    val message: String,
    val severity: CustomToastSeverity,
    val duration: Long,
)