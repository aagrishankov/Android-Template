plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.setup.compose)
}

dependencies{
    api(projects.features.example.impl)
    api(projects.core.storage.impl)
    api(projects.core.network.impl)
    api(projects.core.di)

    implementation(libs.koin.android)
    implementation(libs.koin.core)

    implementation(libs.bundles.feature.impl)

    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.navigation)
    implementation(compose.koin)
}
