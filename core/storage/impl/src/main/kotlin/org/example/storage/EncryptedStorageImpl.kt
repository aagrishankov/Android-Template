package org.example.storage

import android.content.Context
import com.liftric.kvault.KVault
import kotlinx.coroutines.flow.MutableStateFlow

class EncryptedStorageImpl(context: Context) : TokenStorage {

    companion object {
        private const val TOKEN = "AUTH_TOKEN"
    }

    private val store = KVault(context, "AppStorage")

    override val isAuth = MutableStateFlow(getToken().isNotEmpty())

    override fun getToken() =
        store.string(forKey = TOKEN).orEmpty()

    override fun setToken(token: String) {
        store.set(key = TOKEN, stringValue = token)
        isAuth.value = token.isNotEmpty()
    }
}
