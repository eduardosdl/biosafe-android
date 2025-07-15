package com.eduardosdl.biosafe.presentation.components.cardloading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.eduardosdl.biosafe.presentation.components.shimmerloading.shimmerLoading

@Composable
fun CardLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmerLoading()
    )
}