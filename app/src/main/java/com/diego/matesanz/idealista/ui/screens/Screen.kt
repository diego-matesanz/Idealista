package com.diego.matesanz.idealista.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diego.matesanz.idealista.ui.theme.IdealistaTheme

@Composable
fun Screen(
    content: @Composable () -> Unit,
) {
    IdealistaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content,
        )
    }
}
