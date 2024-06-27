plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.buildConfig)
}

buildConfig {
    this.generateAtSync = true
}
