plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.setup.compose)
}

dependencies{
    api(projects.core.network.api)
    implementation(projects.core.di)

    implementation(libs.kotlinx.serialization.json)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.material3)

    api(libs.nota.mvi.core)
    api(libs.nota.mvi.compose)
    api(compose.koin)
}
