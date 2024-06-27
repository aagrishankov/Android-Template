@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.notamedia.ru/repository/maven-releases/") {
            content {
                includeGroupByRegex("ru\\.notamedia.*")
            }
            credentials {
                username = System.getenv("NOTA_MAVEN_USER")
                    ?: providers.gradleProperty("nota.ldap.user").getOrElse("")
                password = System.getenv("NOTA_MAVEN_PASSWORD")
                    ?: providers.gradleProperty("nota.ldap.password").getOrElse("")
            }
        }
    }
}

rootProject.name = "nota_template"
includeBuild("build-logic")
include(
    ":app",

    ":core:base:architecture",
    ":core:base:navigation:api",
    ":core:base:navigation:impl",
    ":core:base:mvi",
    ":core:constants",
    ":core:di",
    ":core:environment",
    ":core:immutable",
    ":core:logger",
    ":core:network:api",
    ":core:network:impl",
    ":core:storage:api",
    ":core:storage:impl",
    ":core:ui:common",
    ":core:ui:res",
    ":core:ui:uikit",

    ":common:umbrella",

    ":features:example:api",
    ":features:example:impl",

)
