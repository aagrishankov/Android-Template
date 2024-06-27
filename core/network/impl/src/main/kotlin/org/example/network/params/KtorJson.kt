package org.example.network.params

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun HttpClientConfig<*>.installJson() {
    install(ContentNegotiation) {
        json(Json {
            isLenient = true
            prettyPrint = false
            ignoreUnknownKeys = true
        })
    }
}
