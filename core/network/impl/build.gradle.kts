plugins {
    alias(libs.plugins.setup.android.library)
}

dependencies {
    api(projects.core.network.api)
    api(projects.core.storage.api)

    implementation(libs.bundles.network.settings)
    implementation(projects.core.di)
}
