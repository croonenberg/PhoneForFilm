// Root-level build.gradle.kts

plugins {
    // Android Gradle Plugin
    id("com.android.application") version "8.9.2" apply false
    id("com.android.library")     version "8.9.2" apply false

    // Kotlin plugins
    kotlin("android") version "2.1.20" apply false

    // KSP (voor Room)
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false
}

tasks.register("clean", Delete::class) {
    // Verwijder de build-directory met de nieuwe API
    delete(rootProject.layout.buildDirectory)
}
