package org.example.mvi

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.network.errors.ExceptionType

@SuppressLint("ModifierParameter")
@Composable
fun ExceptionType.Draw(
    modifier: Modifier = Modifier.fillMaxSize(),
    onClick: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    when (this) {
        ExceptionType.BadGateway -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.BadRequest -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.Forbidden -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.General -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.InternalServerError -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.NetworkTimeout -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.NotAuthorizedException -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.NotFound -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.ServiceUnavailable -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )

        ExceptionType.UnknownHost -> content?.invoke() ?: ErrorUi(
            modifier = modifier,
            title = "",
            description = "",
            onClick = onClick,
            buttonTitle = "Обновить",
        )
    }
}
