package org.example.storage

import android.content.Context
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okio.Path.Companion.toPath
import org.example.storage.models.AppDataStorage

class AppStorageImpl(
    context: Context,
    private val scope: CoroutineScope,
) : AppStorage {

    companion object {
        private const val PATH = "/app_storage.json"
    }

    private val store: KStore<AppDataStorage> = storeOf(
        file = context.cacheDir.path.plus(PATH).toPath(),
        default = AppDataStorage.DEFAULT,
    )

    override val appData: Flow<AppDataStorage>
        get() = store.updates.filterNotNull()

    override fun getStorage(): AppDataStorage {
        return runBlocking(scope.coroutineContext) {
            store.get() ?: AppDataStorage.DEFAULT
        }
    }

    override fun setStorage(data: AppDataStorage) {
        scope.launch { store.set(data) }
    }

    override fun reset() {
        scope.launch { store.reset() }
    }
}
