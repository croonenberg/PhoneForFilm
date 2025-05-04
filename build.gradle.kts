buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
    }
}

plugins {
    id("com.android.application") version "8.9.2" apply false
    id("com.android.library") version "8.9.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id("com.github.ben-manes.versions") version "0.52.0"


    // Kotlin plugins
    kotlin("android") version "2.1.20" apply false
    kotlin("kapt") version "2.1.20" apply false

    // KSP for Room
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false

    // Hilt plugin
    id("dagger.hilt.android.plugin") version "2.56.2" apply false

}

tasks.register<Delete>("clean") {
    // Avoid deprecated 'buildDir'; use layout.buildDirectory instead:
    delete(layout.buildDirectory)
}

