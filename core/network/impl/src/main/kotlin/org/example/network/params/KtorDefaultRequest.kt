@file:Suppress("unused")

package org.example.network.params

import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonElement
import org.example.network.config.NetworkConfig
import org.example.network.errors.NetworkErrors
import org.example.storage.models.DebugDataStorage

fun HttpClientConfig<*>.installDefaultRequest(debugData: DebugDataStorage) {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host =
                if (!NetworkConfig.isDebug) NetworkConfig.DEFAULT_BASE_URL
                else debugData.currentStage
        }
        contentType(ContentType.Application.Json)
    }

    expectSuccess = true
    installHttpResponseValidator()
}

fun HttpClientConfig<*>.installHttpResponseValidator() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            if (exception !is ResponseException) return@handleResponseExceptionWithRequest
            val response = exception.response
            val jsonError = runCatching { response.body<JsonElement>() }.getOrNull()
            val status = response.status.value
            throw NetworkErrors.General(status, jsonError)
        }
    }
}
