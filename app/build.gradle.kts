plugins {
    id("com.android.application") version "8.9.3"
    id("org.jetbrains.kotlin.android") version "2.1.20"
    id("com.google.dagger.hilt.android") version "2.56.2"  // Hilt plugin
    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}



android {
    namespace = "com.example.phoneforfilm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.phoneforfilm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
}

dependencies {
    /* ---- basis AndroidX / Material ---- */
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    /* ---- Hilt ---- */
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    /* ---- Room ---- */
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    /* ---- DataStore Preferences ---- */
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    /* ---- Lifecycle / ViewModel / Activity-KTX ---- */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.activity:activity-ktx:1.9.0")

    /* ---- Coroutines ---- */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.1")

    /* ---- Test ---- */
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}