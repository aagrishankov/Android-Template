package org.example.storage.models

import kotlinx.serialization.Serializable

@Serializable
data class AppDataStorage(
    val uuid: String = "",
) {
    companion object {
        val DEFAULT = AppDataStorage()
    }
}
