plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.nbp.currency.viewer.core.nbpapi.domain"
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
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

}
