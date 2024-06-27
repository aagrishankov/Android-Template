package org.example.example.impl.screens.screen1.mvi

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import org.example.example.api.interactors.ExampleInteractor
import org.example.example.api.models.Example
import org.example.example.impl.paging.ExamplePageSourceFactory
import org.example.mvi.BaseAction
import org.example.mvi.BaseSideEffect
import org.example.mvi.BaseState
import org.example.mvi.BaseStore
import org.example.network.errors.ExceptionType
import org.example.network.errors.toExceptionType
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import ru.notamedia.mvi.intentReduce
import ru.notamedia.mvi.launch

@Stable
data class Screen1State(
    override val isLoading: Boolean = false,
    override val error: ExceptionType? = null,
    val data: List<Example>? = null,
) : BaseState

sealed interface Screen1Action : BaseAction {
    data object OnStart : Screen1Action
}

sealed interface Screen1SideEffect : BaseSideEffect {
    data object OnPush : Screen1SideEffect
}

@Immutable
class Handler(
    val onAction: () -> Unit,
)

class Screen1Store(
    interactor: ExampleInteractor,
    pageSourceFactory: ExamplePageSourceFactory,
) : BaseStore<Screen1State, Screen1Action, Screen1SideEffect>(
    state = Screen1State()
), ExampleInteractor by interactor {

    init {
        dispatch(Screen1Action.OnStart)
    }

    private val ceh = CoroutineExceptionHandler { _, e ->
        intentReduce { it.copy(isLoading = false, error = e.toExceptionType()) }
    }

    private val query = MutableStateFlow<Map<String, String>>(emptyMap())

    @OptIn(ExperimentalCoroutinesApi::class)
    val pager = query.flatMapLatest { query ->
        Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { pageSourceFactory(query) }
        ).flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    val pager = Pager(
//        config = PagingConfig(20),
//        pagingSourceFactory = { pageSourceFactory(emptyMap()) }
//    )

    override fun dispatch(action: Screen1Action) {
        when (action) {
            Screen1Action.OnStart -> intent {
                reduce { state.copy(isLoading = true, error = null) }

                launch(ceh) {
                    update(data = Example.EMPTY)
                }

                reduce { state.copy(isLoading = false) }

                postSideEffect(Screen1SideEffect.OnPush)
            }
        }
    }
}
