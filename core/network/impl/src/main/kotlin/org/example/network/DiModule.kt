package org.example.network

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkDiModule = module {
    singleOf(::httpClient)
}
