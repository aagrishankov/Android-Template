package settings

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

internal fun Project.androidLibrarySetup(
    extension: LibraryExtension,
) = extension.apply {

    val moduleName = path
        .drop(1)
        .replace("-", "_")
        .replace(":", ".")

    val projectPackage = findProperty("projectPackage")
        ?.toString()
        ?: error("Not Found 'projectPackage'")

    namespace = if (moduleName.isNotEmpty()) "$projectPackage.$moduleName" else projectPackage
    compileSdk = findProperty("compileSdk")?.toString()?.toInt()

    defaultConfig {
        minSdk = findProperty("minSdk")?.toString()?.toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildFeatures.buildConfig = true
        buildFeatures.compose = false
        buildFeatures.viewBinding = false
        buildFeatures.aidl = false
        buildFeatures.prefab = false
        buildFeatures.renderScript = false
        buildFeatures.resValues = false
        buildFeatures.shaders = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets["main"].res.srcDirs("src/commonMain/resources", "src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}
