package org.example.network

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.example.network.config.NetworkConfig
import org.example.network.params.ChuckerInterceptor
import org.example.network.params.getUnsafeOkHttpClient
import org.example.network.params.installAuth
import org.example.network.params.installDefaultRequest
import org.example.network.params.installJson
import org.example.network.params.installLogger
import org.example.network.params.installTimeOut
import org.example.storage.DebugStorage

fun httpClient(context: Context, debugStorage: DebugStorage): HttpClient {
    val debugData = debugStorage.getStorage()
    return HttpClient(OkHttp.create {
        if (NetworkConfig.isDebug) {
            if (debugData.isEnabledChucker) preconfigured = getUnsafeOkHttpClient()
            else ChuckerInterceptor.create(context)?.also { addInterceptor(it) }
        }
    }) {
        installDefaultRequest(debugData)
        installTimeOut()
        installJson()
        installLogger()
        installAuth()
    }
}
