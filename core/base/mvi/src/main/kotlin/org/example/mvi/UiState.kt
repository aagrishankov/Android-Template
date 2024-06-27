package org.example.mvi

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.network.errors.ExceptionType
import org.example.network.errors.isError

@SuppressLint("ModifierParameter")
@Composable
fun <T : BaseState> T.UiState(
    modifier: Modifier = Modifier.fillMaxSize(),
    onReload: () -> Unit,
    contentAlignment: Alignment = Alignment.Center,
    errorContent: @Composable (ExceptionType?) -> Unit = {
        it?.Draw(modifier = Modifier.fillMaxSize(), onClick = onReload)
    },
    loadingContent: @Composable () -> Unit = {
        DefaultLoadingState()
    },
    content: @Composable BoxScope.(T) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        content = {
            when {
                error.isError -> errorContent(error)
                isLoading -> loadingContent()
                else -> content(this@UiState)
            }
        }
    )
}

@Composable
fun DefaultLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
