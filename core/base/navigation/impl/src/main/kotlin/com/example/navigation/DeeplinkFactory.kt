package com.example.navigation

import android.content.Intent
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import org.example.env.Environment

fun String.createDeeplink(): List<NavDeepLink> {

    val deeplink = this
        .takeIf { it.isNotEmpty() }
        ?: return emptyList()

    return Environment.webStages.map { stage ->
        navDeepLink {
            uriPattern = buildString {
                append(stage)
                append(deeplink)
            }
            action = Intent.ACTION_VIEW
        }
    }.apply {
        forEach { println(it.uriPattern) }
    }
}
