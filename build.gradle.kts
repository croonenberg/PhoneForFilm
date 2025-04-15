buildscript {
    dependencies {
        // Voeg de Kotlin plugin toe via de juiste versie
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    kotlin("android") version "1.8.0" apply false
}
