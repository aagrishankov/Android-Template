package org.example.network.params

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout

private const val TIME_OUT_MILLIS = 45_000L

fun HttpClientConfig<*>.installTimeOut() {
    install(HttpTimeout) {
        requestTimeoutMillis = TIME_OUT_MILLIS
        connectTimeoutMillis = TIME_OUT_MILLIS
        socketTimeoutMillis = TIME_OUT_MILLIS
    }
}
