@file:Suppress("unused")

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import config.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import settings.androidLibrarySetup
import settings.androidApplicationSetup

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.get().pluginId)
            apply(libs.plugins.android.library.get().pluginId)
            apply(libs.plugins.kotlin.android.get().pluginId)
            apply(libs.plugins.kotlin.serialx.get().pluginId)
        }
        extensions.configure<LibraryExtension>(::androidLibrarySetup)
    }
}

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
            apply(libs.plugins.compose.core.get().pluginId)
        }

        val compose = extensions
            .getByType<ComposeExtension>().dependencies

        dependencies.add("api", compose.runtime)
    }
}

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.get().pluginId)
            apply(libs.plugins.android.application.get().pluginId)
            apply(libs.plugins.kotlin.android.get().pluginId)
            apply(libs.plugins.kotlin.serialx.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
        }
        extensions.configure<BaseAppModuleExtension>(::androidApplicationSetup)
    }
}
