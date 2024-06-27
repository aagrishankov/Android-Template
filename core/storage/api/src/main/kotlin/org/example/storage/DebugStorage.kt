package org.example.storage

import kotlinx.coroutines.flow.Flow
import org.example.storage.models.DebugDataStorage

interface DebugStorage {
    val debugData: Flow<DebugDataStorage>
    fun getStorage(): DebugDataStorage
    fun setStorage(data: DebugDataStorage)
    fun reset()
}
