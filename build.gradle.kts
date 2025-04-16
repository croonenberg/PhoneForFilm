// Root build.gradle.kts

plugins {
    kotlin("jvm") version "1.9.10" apply false
    kotlin("kapt") version "1.9.10" apply false
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
