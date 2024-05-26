@file:Suppress("unused")

package settings

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

internal fun Project.androidApplicationSetup(
    extension: BaseAppModuleExtension,
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
        versionCode = calculateVersionCode()
        versionName = findProperty("versionName")?.toString() ?: error("Not Found 'versionName'")
        minSdk = findProperty("minSdk")?.toString()?.toInt()
        targetSdk = findProperty("targetSdk")?.toString()?.toInt()
        applicationId =
            findProperty("applicationId")?.toString() ?: error("Not Found 'applicationId'")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildFeatures.buildConfig = true
        buildFeatures.compose = true
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

    applicationVariants.all {
        outputs
            .mapNotNull { it as? BaseVariantOutputImpl }
            .forEach { output ->
                val pattern = findProperty("apkPattern")?.toString()
                    ?: error("Not Found 'Apk Pattern'")

                val newApkName = pattern
                    .replace("\$versionName", "-$versionName")
                    .replace("\$versionCode", "-$versionCode")
                    .replace(
                        "\$flavorName",
                        "-$flavorName".takeIf { flavorName.isNotEmpty() }.orEmpty()
                    )
                    .replace("\$buildType", if (name.contains("debug", true)) "-debug" else "")

                output.outputFileName = newApkName
            }
    }

    sourceSets["main"].res.srcDirs("src/commonMain/resources", "src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

private fun Project.calculateVersionCode(): Int {
    return runCatching {

        val gitLogCommand = "git rev-list --count HEAD"
        val process = ProcessBuilder(gitLogCommand.split(" "))
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        process.inputStream
            .bufferedReader()
            .use { it.readLine().trim().toInt() }

    }.getOrElse {

        println("WARNING: Not found git commits, checking local Version Code")

        findProperty("localVersionCode")?.toString()?.toIntOrNull()
            ?: error("Not Found 'localVersionCode'")
    }
}
