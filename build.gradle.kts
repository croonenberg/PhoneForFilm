// Root-level build.gradle.kts
// Defines plugin versions and global tasks for the entire project

plugins {
    // Android Gradle Plugin versions (apply false to defer to subprojects)
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library") version "8.9.1" apply false
    // Kotlin Android plugin
    kotlin("android") version "1.9.0" apply false
    kotlin("kapt")    version "1.9.0" apply false

    // KSP (Kotlin Symbol Processing)
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}

tasks.register("clean", Delete::class) {
    // nieuwere API ipv deprecated buildDir
    delete(rootProject.layout.buildDirectory)
}
}

// Global clean task to delete the build directory of the root project
tasks.register("clean", Delete::class) {
    // Use the new buildDirectory API instead of the deprecated buildDir property
    delete(rootProject.layout.buildDirectory)
}
