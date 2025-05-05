android {
    plugins {
        id("com.android.application") version "8.9.2" apply true
        id("org.jetbrains.kotlin.android") version "2.1.20" apply true
        id("kotlin-kapt")
        id("dagger.hilt.android.plugin")
    }
    namespace = "com.example.phoneforfilm"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.phoneforfilm"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        bundle {
            language {
                enableSplit = false
            }
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }

        dependencies {
            implementation("androidx.core:core-ktx:1.16.0")
            implementation("androidx.appcompat:appcompat:1.7.0")
            implementation("com.google.android.material:material:1.12.0")
            implementation("androidx.constraintlayout:constraintlayout:2.2.1")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
            implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
            implementation("androidx.activity:activity-ktx:1.10.1")
            implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20")
            implementation("com.google.android.material:material:1.12.0")


            // Room
            implementation("androidx.room:room-runtime:2.7.1")
            kapt("androidx.room:room-compiler:2.7.1")
            implementation("androidx.room:room-ktx:2.7.1")

            // Hilt
            implementation("com.google.dagger:hilt-android:2.56.2")
            kapt("com.google.dagger:hilt-compiler:2.56.2")
            implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
        }
    }
}
