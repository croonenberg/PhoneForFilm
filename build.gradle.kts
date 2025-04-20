// Root-level build.gradle.kts

plugins {
    // Android Gradle Plugin
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library")     version "8.9.1" apply false

    // Kotlin plugins
    kotlin("android") version "1.9.0" apply false

    // KSP (voor Room)
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}

tasks.register("clean", Delete::class) {
    // Verwijder de build-directory met de nieuwe API
    delete(rootProject.layout.buildDirectory)
}
