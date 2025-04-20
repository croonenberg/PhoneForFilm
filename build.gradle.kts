// Root-level build.gradle.kts

plugins {
    // Android Gradle Plugin
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library")     version "8.9.1" apply false

    // Kotlin plugins
    kotlin("android") version "2.1.10" apply false

    // KSP (voor Room)
    id("com.google.devtools.ksp") version "1.9.22-1.0.18" apply false
}

tasks.register("clean", Delete::class) {
    // Verwijder de build-directory met de nieuwe API
    delete(rootProject.layout.buildDirectory)
}
