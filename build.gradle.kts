plugins {
    // Android Gradle Plugin versions (apply false to defer to subprojects)
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    // Kotlin Android plugin
    kotlin("android") version "1.9.0" apply false
}

// Global clean task to delete the build directory of the root project
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
