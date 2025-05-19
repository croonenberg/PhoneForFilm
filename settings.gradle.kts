// settings.gradle.kts  (project-root)
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.4.0"       // ← vul jouw AGP-versie in
        id("org.jetbrains.kotlin.android") version "1.9.22"
        id("org.jetbrains.kotlin.kapt")   version "1.9.22"
        id("com.google.dagger.hilt.android") version "2.52"
        id("io.gitlab.arturbosch.detekt")  version "1.23.3"
    }
}

rootProject.name = "PhoneForFilm"
include(":app")
