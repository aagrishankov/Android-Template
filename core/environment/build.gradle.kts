@file:Suppress("UNCHECKED_CAST")

plugins {
    alias(libs.plugins.setup.android.library)
    alias(libs.plugins.buildConfig)
}

buildConfig {
    this.generateAtSync = true

    this.buildConfigField("DEFAULT_BASE_URL", findProperty("DEFAULT_BASE_URL").toString())
    this.buildConfigField("stages", (findProperty("STAGES") as? List<String>).orEmpty())

    this.buildConfigField("DEFAULT_BASE_WEB_URL", findProperty("DEFAULT_BASE_WEB_URL").toString())
    this.buildConfigField("webStages", (findProperty("WEB_STAGES") as? List<String>).orEmpty())

    this.buildConfigField("IS_DEV_STAGE", findProperty("IS_DEV_STAGE").toString().toBoolean())
}
