// Root build.gradle.kts

plugins {
    kotlin("jvm") version "2.1.10" apply false
    kotlin("kapt") version "2.1.10" apply false
    id("com.android.application") version "8.6.1" apply false
    id("com.android.library") version "8.6.1" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
