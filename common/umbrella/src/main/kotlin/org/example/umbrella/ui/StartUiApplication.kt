package org.example.umbrella.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.example.example.api.navigation.ExampleNavigation
import org.example.example.impl.screens.navigationExample

@Composable
fun StartApplication() {

    val nav = rememberNavController()

    NavHost(nav, startDestination = ExampleNavigation.ExampleScreen1) {
        navigationExample(nav)
    }
}
