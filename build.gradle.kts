plugins {
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android") apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

detekt {
    config.setFrom(rootProject.file("detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    jvmTarget = "17"
}
