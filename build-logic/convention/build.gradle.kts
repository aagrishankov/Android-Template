plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.bundles.gradle.plugins)
}

gradlePlugin {
    plugins {
        register("android-library-setup") {
            id = "android-library-setup"
            implementationClass = "AndroidLibraryPlugin"
            version = "1.0.0"
        }
    }
    plugins {
        register("android-application-setup") {
            id = "android-application-setup"
            implementationClass = "AndroidApplicationPlugin"
            version = "1.0.0"
        }
    }
    plugins {
        register("compose-setup") {
            id = "compose-setup"
            implementationClass = "ComposePlugin"
            version = "1.0.0"
        }
    }
}
