package org.example.example.impl.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.example.navigation.createDeeplink
import org.example.example.api.navigation.ExampleNavigation
import org.example.example.impl.screens.screen1.ExampleScreen1
import org.example.example.impl.screens.screen2.ExampleScreen2
import kotlin.reflect.typeOf

fun NavGraphBuilder.navigationExample(nav: NavController) {

    composable<ExampleNavigation.ExampleScreen1>(
        deepLinks = ExampleNavigation.DEEPLINK_EXAMPLE1.createDeeplink(),
    ) {
        ExampleScreen1(nav)
    }

    composable<ExampleNavigation.ExampleScreen2>(
        deepLinks = ExampleNavigation.DEEPLINK_EXAMPLE2.createDeeplink(),
        typeMap = mapOf(
            typeOf<Int>() to NavType.IntType,
        )
    ) {
        ExampleScreen2(nav, it)
    }
}
