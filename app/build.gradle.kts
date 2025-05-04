// File: app/build.gradle.kts

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.phoneforfilm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.phoneforfilm"
        minSdk = 21
        //noinspection OldTargetApi
        targetSdk = 35
        versionCode = 20
        versionName = "19.0.6"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        dataBinding = true
    }


    dependencies {
        // AndroidX core & UI
        implementation("androidx.core:core-ktx:1.16.0")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.constraintlayout:constraintlayout:2.2.1")
        implementation("androidx.recyclerview:recyclerview:1.4.0")
        implementation("androidx.activity:activity-ktx:1.10.1")
        implementation("androidx.fragment:fragment-ktx:1.8.6")

        // Lifecycle (ViewModel, LiveData)
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
        kapt("androidx.lifecycle:lifecycle-compiler:2.8.7")

        // Coroutines & Flow
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

        // Room (database, DAO, entities)
        implementation("androidx.room:room-runtime:2.7.1")
        implementation("androidx.room:room-ktx:2.7.1")
        kapt("androidx.room:room-compiler:2.7.1")

        // Hilt for DI
        implementation("com.google.dagger:hilt-android:2.56.2")
        kapt("com.google.dagger:hilt-android-compiler:2.56.2")

        // Navigation Component
        implementation("androidx.navigation:navigation-fragment-ktx:2.8.9")
        implementation("androidx.navigation:navigation-ui-ktx:2.8.9")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    }

}
