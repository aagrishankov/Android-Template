package org.example.example.impl.screens.screen1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.example.impl.screens.screen1.mvi.Handler
import org.example.example.impl.screens.screen1.mvi.Screen1SideEffect
import org.example.example.impl.screens.screen1.mvi.Screen1Store
import org.example.mvi.rememberKoinStore
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ExampleScreen1(nav: NavController) {
    val store = rememberKoinStore<Screen1Store>()

    val state by store.collectAsState()

    val a = 4

    val handler = Handler(
//        onAction = { nav.navigate(ExampleNavigation.ExampleScreen2(2)) }
//        onAction = { nav.navigate("devWeb/example2?number=2") }
        onAction = { nav.navigate("devWeb/example2/$a") }
    )

    val pager = store.pager.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {


        items(pager) { item ->
            item?.also {
                Text(text = it.name, modifier = Modifier.padding(8.dp))
            }
        }


        when (pager.loadState.refresh) {
            is LoadState.Loading -> {
                item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
            }
            is LoadState.Error -> {
                item { Text("Error loading data", modifier = Modifier.padding(16.dp)) }
            }
            else -> Unit
        }
    }

//    ExampleScreen1View(state, handler)

    store.collectSideEffect { effect ->
        when (effect) {
            Screen1SideEffect.OnPush -> println("OnPush")
        }
    }
}
