plugins {
    id("com.android.application") version "8.9.3" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false // Hilt plugin
    id("org.jetbrains.kotlin.kapt") version "2.1.20" apply false   // <- optioneel, voorkomt IDE-hint
}

detekt {
    config.setFrom(rootProject.file("detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    jvmTarget = "17"
}
