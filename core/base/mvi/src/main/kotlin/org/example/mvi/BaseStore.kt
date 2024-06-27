package org.example.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import org.example.network.errors.ExceptionType
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import ru.notamedia.mvi.NotaAction
import ru.notamedia.mvi.NotaMviStore
import ru.notamedia.mvi.NotaSideEffect
import ru.notamedia.mvi.NotaState

@Stable
interface BaseState : NotaState {
    val isLoading: Boolean
    val error: ExceptionType?
}

@Stable
interface BaseAction : NotaAction

@Stable
interface BaseSideEffect : NotaSideEffect

@Stable
abstract class BaseStore<S : BaseState, A : BaseAction, SE : BaseSideEffect>(state: S) :
    NotaMviStore<S, SE, A>(state = state)

@Composable
fun <T : BaseStore<*, *, *>> rememberStore(
    factory: @DisallowComposableCalls () -> T,
    isCleanStore: Boolean = true,
): T {
    val viewModel = remember { factory() }
    if (isCleanStore) DisposableEffect(Unit) {
        onDispose(viewModel::onDestroy)
    }
    return viewModel
}

@Composable
fun <T : BaseStore<*, *, *>> rememberStore(
    store: T,
    isCleanStore: Boolean = true,
): T {
    val viewModel = remember { store }
    if (isCleanStore) DisposableEffect(Unit) {
        onDispose(viewModel::onDestroy)
    }
    return viewModel
}

@Composable
inline fun <reified T : BaseStore<*, *, *>> rememberKoinStore(
    key: String? = null,
    noinline parameters: ParametersDefinition? = null,
    isCleanStore: Boolean = true,
): T {
    val viewModel = koinViewModel<T>(key = key, parameters = parameters)
    if (isCleanStore) DisposableEffect(Unit) {
        onDispose(viewModel::onDestroy)
    }
    return viewModel
}
