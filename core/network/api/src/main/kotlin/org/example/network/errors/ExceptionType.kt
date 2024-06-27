package org.example.network.errors

import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ExceptionType {
    data object NetworkTimeout : ExceptionType() // TimeOut
    data object General : ExceptionType()
    data object UnknownHost : ExceptionType() // Internet
    data object BadRequest : ExceptionType() // 400 HTTP ERROR
    data object NotAuthorizedException : ExceptionType() // 401 HTTP ERROR
    data object Forbidden : ExceptionType() // 403 HTTP ERROR
    data object NotFound : ExceptionType() // 404 HTTP ERROR
    data object InternalServerError : ExceptionType() // 500 HTTP ERROR
    data object BadGateway : ExceptionType() // 502 HTTP ERROR
    data object ServiceUnavailable : ExceptionType() // 503 HTTP ERROR
}

val ExceptionType?.isError
    get() = this != null

val Throwable.jsonError
    get() = when (this) {
        is NetworkErrors.General -> json
        else -> null
    }

fun Throwable.toExceptionType(): ExceptionType = when (this) {
    is NetworkErrors.General -> handleErrorCode(code)
    is SocketTimeoutException -> ExceptionType.NetworkTimeout
    is UnknownHostException -> ExceptionType.UnknownHost
    else -> ExceptionType.General
}

internal fun handleErrorCode(code: Int): ExceptionType {
    return when (code) {
        400 -> ExceptionType.BadRequest
        401 -> ExceptionType.NotAuthorizedException
        403 -> ExceptionType.Forbidden
        404 -> ExceptionType.NotFound
        500 -> ExceptionType.InternalServerError
        502 -> ExceptionType.BadGateway
        503 -> ExceptionType.ServiceUnavailable
        else -> ExceptionType.General
    }
}
