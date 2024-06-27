plugins {
    alias(libs.plugins.setup.android.application)
    alias(libs.plugins.setup.compose)
}

val projectName = findProperty("projectName")?.toString()
    ?: error("Not found 'projectName'")

android {
    kotlinOptions.jvmTarget = "17"

    val devStageUrl = findProperty("devStageUrl").toString()
    val prodStageUrl = findProperty("prodStageUrl").toString()
    rootProject.ext.set("API_STAGES", listOf(devStageUrl, prodStageUrl))

    val devStageWebUrl = findProperty("devStageWebUrl").toString()
    val prodStageWebUrl = findProperty("prodStageWebUrl").toString()
    rootProject.ext.set("WEB_STAGES", listOf(devStageWebUrl, prodStageWebUrl))

    val devSign by signingConfigs.creating {
        storeFile = file(findProperty("localStoreFile")?.toString() ?: "nothing")
        storePassword = findProperty("localStorePassword").toString()
        keyAlias = findProperty("localKeyAlias").toString()
        keyPassword = findProperty("localKeyPassword").toString()
    }

    val prodSign by signingConfigs.creating {
        val name = projectName.uppercase()
        storeFile = file(System.getenv("RELEASE_${name}_PROD_STORE_FILE") ?: "nothing")
        storePassword = System.getenv("RELEASE_${name}_PROD_STORE_PASSWORD").orEmpty()
        keyAlias = System.getenv("RELEASE_${name}_PROD_KEY_ALIAS").orEmpty()
        keyPassword = System.getenv("RELEASE_${name}_PROD_KEY_PASSWORD").orEmpty()
    }

    flavorDimensions += listOf("stage", "services")

    val gms by productFlavors.creating {
        dimension = "services"
        versionNameSuffix = "-gms"
    }

    val dev by productFlavors.creating {
        dimension = "stage"
        versionNameSuffix = "-dev"
        applicationIdSuffix = ".debug"
        signingConfig = devSign
        rootProject.ext.set("DEFAULT_BASE_URL", devStageUrl) //TODO
        rootProject.ext.set("DEFAULT_BASE_WEB_URL", devStageWebUrl) //TODO
        rootProject.ext.set("IS_DEV_STAGE", true)
    }

    val prod by productFlavors.creating {
        dimension = "stage"
        versionNameSuffix = "-prod"
        signingConfig = prodSign
        rootProject.ext.set("DEFAULT_BASE_URL", prodStageUrl) //TODO
        rootProject.ext.set("DEFAULT_BASE_WEB_URL", prodStageWebUrl) //TODO
        rootProject.ext.set("IS_DEV_STAGE", false)
    }
}

dependencies {
    implementation(libs.bundles.android.application)
    implementation(projects.common.umbrella)
}
