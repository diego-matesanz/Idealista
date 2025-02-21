package com.diego.matesanz.idealista.ui.screens.productDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diego.matesanz.idealista.ui.screens.Screen

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    modifier: Modifier = Modifier,
) {
    Screen {
        Scaffold(
            modifier = modifier,
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding),
            ) {

            }
        }
    }
}
