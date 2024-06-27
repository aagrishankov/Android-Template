package org.example.example.impl.screens.screen1.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.example.impl.screens.screen1.mvi.Handler
import org.example.example.impl.screens.screen1.mvi.Screen1State
import org.example.mvi.UiState

@Composable
fun ExampleScreen1View(state: Screen1State, handler: Handler) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            state.UiState(
                onReload = handler.onAction,
                content = {
                    Text(text = "Hello World")
                }
            )
        }
    }
}
