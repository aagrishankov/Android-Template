package org.example.network.params

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess

fun HttpClientConfig<*>.installRetryConnection() {
    install(HttpRequestRetry) {
        maxRetries = 1
        retryIf { req, response ->
            !response.status.isSuccess() && req.method == HttpMethod.Get
        }
        exponentialDelay()
        modifyRequest { request ->
            request.headers.append("x-retry-count", retryCount.toString())
        }
    }
}
