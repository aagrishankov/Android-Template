plugins {
    alias(libs.plugins.setup.android.library)
}

dependencies {
    implementation(projects.core.environment)
    implementation(libs.logger)
}
