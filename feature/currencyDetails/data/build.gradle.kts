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
    namespace = "com.nbp.currency.viewer.feature.detail.data"
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
    api(projects.core.nbpapi.domain)
    implementation(projects.feature.currencyDetails.domain)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

}
