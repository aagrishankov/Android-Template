package org.example.network.errors

import kotlinx.serialization.json.JsonElement

sealed class NetworkErrors(
    open val code: Int,
    open val json: JsonElement?
) : Throwable(json.toString()) {

    data class General(
        override val code: Int,
        override val json: JsonElement?
    ) : NetworkErrors(code, json)
}
