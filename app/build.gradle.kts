plugins {
    alias(libs.plugins.setup.android.application)
    alias(libs.plugins.setup.compose)
}

val projectName = findProperty("projectName")?.toString()
    ?: error("Not found 'projectName'")

android {
    kotlinOptions.jvmTarget = "17"

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
        versionNameSuffix = "-demo"
        applicationIdSuffix = ".debug"
        signingConfig = devSign
    }

    val prod by productFlavors.creating {
        dimension = "stage"
        versionNameSuffix = "-prod"
        signingConfig = prodSign
    }
}

dependencies {

    implementation(libs.bundles.android.application)

    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.foundation)

    implementation(compose.preview)
    implementation(compose.uiTooling)

}
