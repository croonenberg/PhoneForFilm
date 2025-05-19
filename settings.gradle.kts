// settings.gradle.kts  (project-root)
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.9.1"       // ← vul jouw AGP-versie in
        id("org.jetbrains.kotlin.android") version "2.1.21"
        id("org.jetbrains.kotlin.kapt")   version "2.1.21"
        id("com.google.dagger.hilt.android") version "2.56.2"
        id("io.gitlab.arturbosch.detekt")  version "1.23.3"
    }
}

rootProject.name = "PhoneForFilm"
include(":app")
