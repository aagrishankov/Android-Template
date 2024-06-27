package org.example.storage

import kotlinx.coroutines.flow.Flow
import org.example.storage.models.AppDataStorage

interface AppStorage {
    val appData: Flow<AppDataStorage>
    fun getStorage(): AppDataStorage
    fun setStorage(data: AppDataStorage)
    fun reset()
}
