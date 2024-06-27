package org.example.storage

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storageDiModule = module {
    singleOf(::EncryptedStorageImpl) {
        binds(listOf(TokenStorage::class))
        createdAtStart()
    }
    singleOf(::AppStorageImpl) {
        bind<AppStorage>()
        createdAtStart()
    }
    singleOf(::DebugStorageImpl) {
        bind<DebugStorage>()
        createdAtStart()
    }
}
