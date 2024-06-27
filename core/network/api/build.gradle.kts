plugins {
    alias(libs.plugins.setup.android.library)
}

dependencies {
    implementation(projects.core.environment)

    api(libs.kotlinx.serialization.json)
}
