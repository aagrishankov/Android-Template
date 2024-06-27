package settings

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import java.util.Properties

fun Project.secretsSetup() {
    val secrets = rootProject.file("secrets.properties")
    if (secrets.exists()) {
        val localProperties = Properties()
        localProperties.load(secrets.inputStream())
        localProperties.forEach { (key, value) ->
            when(value) {
                is String -> rootProject.extraProperties.set(key.toString(), value)
                is Number -> rootProject.extraProperties.set(key.toString(), value)
                is Boolean -> rootProject.extraProperties.set(key.toString(), value)
                else -> Unit
            }
        }
    } else {
        secrets.createNewFile()
        println("WARNING: Not found 'secrets.properties', creating file..")
    }
}
