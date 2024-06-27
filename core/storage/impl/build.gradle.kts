plugins {
    alias(libs.plugins.setup.android.library)
}

dependencies {
    api(projects.core.storage.api)
    implementation(projects.core.di)

    implementation(libs.kvault)
    implementation(libs.kstore.core)
    implementation(libs.kstore.file)
}
