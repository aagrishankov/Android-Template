@file:Suppress("unused")

package org.example.di

import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools

object Injector : KoinComponent {

    inline fun <reified T : Any> get(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null,
    ): T = getKoin().get(qualifier, parameters)

    inline fun <reified T : Any> lazy(
        qualifier: Qualifier? = null,
        mode: LazyThreadSafetyMode = KoinPlatformTools.defaultLazyMode(),
        noinline parameters: ParametersDefinition? = null,
    ): Lazy<T> = getKoin().inject(qualifier, mode, parameters)
}
