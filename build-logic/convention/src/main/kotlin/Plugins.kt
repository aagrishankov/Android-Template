@file:Suppress("unused")

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import config.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import settings.androidApplicationSetup
import settings.androidLibrarySetup
import tasks.registerTasks

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.core.get().pluginId)
            apply(libs.plugins.android.application.get().pluginId)
            apply(libs.plugins.kotlin.android.get().pluginId)
            apply(libs.plugins.kotlin.serialx.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
        }
        extensions.configure<BaseAppModuleExtension>(::androidApplicationSetup)
        dependencies.add("detektPlugins", libs.detekt.formatting)

        registerTasks()
    }
}

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.core.get().pluginId)
            apply(libs.plugins.android.library.get().pluginId)
            apply(libs.plugins.kotlin.android.get().pluginId)
            apply(libs.plugins.kotlin.serialx.get().pluginId)
        }
        extensions.configure<LibraryExtension>(::androidLibrarySetup)
        dependencies.add("detektPlugins", libs.detekt.formatting)

        registerTasks()
    }
}

class ComposePlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.detekt.core.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
        }
        dependencies.add("api", libs.compose.runtime)

        project.extensions.create("compose", ComposeDependencies::class, target)
    }

    open class ComposeDependencies(private val project: Project) {
        // Уже подключен при инициализации плагина по умолчанию
        internal val runtime: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.runtime
        val foundation: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.foundation
        val ui: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.ui.asProvider()
        val preview: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.ui.tooling.preview
        val previewDebug: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.ui.tooling.debug
        val material3: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.material3
        val activity: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.activity
        val viewModel: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.viewModel
        val navigation: Provider<MinimalExternalModuleDependency>
            get() = project.libs.compose.navigation
        val koin: Provider<MinimalExternalModuleDependency>
            get() = project.libs.koin.compose
    }
}
