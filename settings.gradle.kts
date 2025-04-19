pluginManagement {
    repositories {
        gradlePluginPortal() // Kotlin plugins, etc.
        google()              // Android plugins
        mavenCentral()        // Other dependencies
    }
    plugins {
        id("com.android.application") version "8.9.1" apply false
        id("com.android.library")     version "8.9.1" apply false
        id("org.jetbrains.kotlin.android") version "2.1.10" apply false
        // id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PhoneForFilm"
include(":app")
