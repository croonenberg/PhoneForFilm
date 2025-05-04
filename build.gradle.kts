plugins {
    id("com.android.application") version "8.9.2" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false
    kotlin("android") version "2.1.20" apply false
    kotlin("kapt") version "2.1.20" apply false

}
tasks.register<Delete>("clean") {
    // Avoid deprecated 'buildDir'; use layout.buildDirectory instead:
    delete(layout.buildDirectory)
}

