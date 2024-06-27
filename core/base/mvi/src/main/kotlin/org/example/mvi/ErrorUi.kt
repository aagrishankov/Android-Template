package org.example.mvi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorUi(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    buttonTitle: String,
    onClick: (() -> Unit)?,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        )
        Spacer(modifier = Modifier.height(14.dp))
        description?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = description,
                textAlign = TextAlign.Center,
            )
        }
        if (onClick != null) {
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                modifier = Modifier,
                onClick = { onClick() },
                content = {
                    Text(
                        text = buttonTitle,
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    }
}
