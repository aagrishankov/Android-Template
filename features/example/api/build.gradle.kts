plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.setup.compose)
}

dependencies {
    api(projects.core.base.navigation.api)
    api(libs.bundles.feature.api)
}
