package org.example.network.params

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import org.example.di.Injector
import org.example.storage.TokenStorage

fun HttpClientConfig<*>.installAuth() {
    val tokenStorage by Injector.lazy<TokenStorage>()
    install(Auth) {
        bearer {
            loadTokens {
                tokenStorage
                    .getToken()
                    .takeIf { it.isNotEmpty() }
                    ?.let { BearerTokens(it, "") }
            }
            refreshTokens {
                null // TODO
            }
        }
    }
}
