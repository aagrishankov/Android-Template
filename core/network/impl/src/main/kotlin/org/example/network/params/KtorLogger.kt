package org.example.network.params

import android.util.Log
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.example.network.config.NetworkConfig

fun HttpClientConfig<*>.installLogger() {
    if (NetworkConfig.isDebug) install(Logging) {
        logger = ktorLogger
        level = LogLevel.ALL
    }
}

private val ktorLogger: Logger = object : Logger {
    override fun log(message: String) {
        dLong("KTOR", message)
    }
}

private const val MAX_LENGTH = 4000

fun dLong(tag: String, message: String) {
    if (message.length <= MAX_LENGTH) {
        Log.d(tag, message)
    } else {
        var startIndex = 0
        var endIndex = MAX_LENGTH

        while (startIndex < message.length) {
            if (endIndex > message.length)
                endIndex = message.length

            val part = message.substring(startIndex, endIndex)
            Log.d(tag, part)

            startIndex = endIndex
            endIndex += MAX_LENGTH
        }
    }
}
