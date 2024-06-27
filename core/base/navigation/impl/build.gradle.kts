plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.setup.compose)
}

dependencies{
    implementation(projects.core.base.navigation.api)
    implementation(projects.core.environment)

    implementation(libs.kotlinx.serialization.json)
    api(compose.navigation)
}
