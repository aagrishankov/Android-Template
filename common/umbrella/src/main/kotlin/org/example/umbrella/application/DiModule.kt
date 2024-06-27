package org.example.umbrella.application

import org.example.example.impl.di.exampleDiModule
import org.example.network.networkDiModule
import org.example.storage.storageDiModule

val diModules = listOf(
    storageDiModule,
    networkDiModule,

    exampleDiModule,
)
