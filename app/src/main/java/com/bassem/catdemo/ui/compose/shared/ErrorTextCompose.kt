package com.bassem.catdemo.ui.compose.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorTextCompose(message: String?) {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!message.isNullOrEmpty()) {
            Text(
                text = message,
                color = Color.Red,

                )
        }

    }

}