plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.nbp.currency.viewer.core.navigation"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    kotlin {
        jvmToolchain(21)
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
