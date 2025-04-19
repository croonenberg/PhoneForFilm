plugins {
    id("com.android.application")
    kotlin("android")
    // id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.phoneforfilm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.phoneforfilm"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    // Room with KSP
    implementation("androidx.room:room-runtime:2.6.2")
    implementation("androidx.room:room-compiler:2.6.2")
    implementation("androidx.room:room-ktx:2.6.2")
}
