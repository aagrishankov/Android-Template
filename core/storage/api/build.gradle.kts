plugins {
    alias(libs.plugins.setup.android.library)
}

dependencies {
    implementation(projects.core.environment)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.serialization.json)
}
