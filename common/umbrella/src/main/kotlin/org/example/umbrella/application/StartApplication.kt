package org.example.umbrella.application

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun startApplication(context: Context) {
    startKoin {
        androidContext(context)

        val appModule = module {
            single { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
        }

        modules(
            appModule + diModules
        )
    }
}
