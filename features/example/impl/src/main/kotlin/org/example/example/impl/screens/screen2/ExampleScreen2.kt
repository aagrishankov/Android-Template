package org.example.example.impl.screens.screen2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import org.example.example.api.navigation.ExampleNavigation

@Composable
fun ExampleScreen2(nav: NavController, stack: NavBackStackEntry) {

    val data = stack.toRoute<ExampleNavigation.ExampleScreen2>()

    LaunchedEffect(Unit) {
        delay(5000)
        nav.popBackStack()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = data.number.toString())
    }
}
