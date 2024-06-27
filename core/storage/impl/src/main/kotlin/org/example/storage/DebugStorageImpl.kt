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
import org.example.storage.models.DebugDataStorage

class DebugStorageImpl(
    context: Context,
    private val scope: CoroutineScope,
) : DebugStorage {

    companion object {
        private const val PATH = "/debug_storage.json"
    }

    private val store: KStore<DebugDataStorage> = storeOf(
        file = context.cacheDir.path.plus(PATH).toPath(),
        default = DebugDataStorage.DEFAULT,
    )

    override val debugData: Flow<DebugDataStorage>
        get() = store.updates.filterNotNull()

    override fun getStorage(): DebugDataStorage {
        return runBlocking(scope.coroutineContext) {
            store.get() ?: DebugDataStorage.DEFAULT
        }
    }

    override fun setStorage(data: DebugDataStorage) {
        scope.launch { store.set(data) }
    }

    override fun reset() {
        scope.launch { store.reset() }
    }
}
