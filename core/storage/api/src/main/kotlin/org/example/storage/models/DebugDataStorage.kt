package org.example.storage.models

import kotlinx.serialization.Serializable
import org.example.env.Environment

@Serializable
data class DebugDataStorage(
    val isEnabledChucker: Boolean = false,
    val currentStage: String = Environment.DEFAULT_BASE_URL,
    val stages: List<String> = Environment.stages,
) {
    companion object {
        val DEFAULT = DebugDataStorage()
    }
}
