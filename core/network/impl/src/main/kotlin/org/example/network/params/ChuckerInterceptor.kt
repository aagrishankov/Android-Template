package org.example.network.params

import android.content.Context
import okhttp3.Interceptor

object ChuckerInterceptor {

    fun create(context: Context): Interceptor? {
        return runCatching {
            val builder = Class.forName("com.chuckerteam.chucker.api.ChuckerInterceptor")

            val instance = builder
                .getConstructor(Context::class.java)
                .newInstance(context)

            instance as? Interceptor
        }.getOrNull()
    }
}
