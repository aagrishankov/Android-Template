package org.example.example.api.navigation

import com.example.navigation.NavigationScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class ExampleNavigation : NavigationScreen() {

    companion object {
        const val DEEPLINK_EXAMPLE1: String = "/example1"
//        const val DEEPLINK_EXAMPLE2: String = "/example2?number={number}"
        const val DEEPLINK_EXAMPLE2: String = "/example2/{number}"
    }

    @Serializable
    data object ExampleScreen1 : ExampleNavigation()

    @Serializable
    data class ExampleScreen2(val number: Int) : ExampleNavigation()
}
