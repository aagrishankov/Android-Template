plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.setup.compose)
}

dependencies {
    api(projects.features.example.api)

    implementation(projects.core.di)
    implementation(projects.core.base.architecture)
    implementation(projects.core.base.navigation.impl)
    implementation(projects.core.base.mvi)
    implementation(projects.core.immutable)

    implementation(libs.bundles.feature.impl)

    implementation(libs.pagination.common)
    implementation(libs.pagination.compose)

    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.navigation)
    implementation(compose.koin)
}
