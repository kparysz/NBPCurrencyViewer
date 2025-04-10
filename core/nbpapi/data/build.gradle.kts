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
    namespace = "com.nbp.currency.viewer.core.nbpapi.data" +
            ""
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
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.serialization)
    implementation(projects.core.nbpapi.domain)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

}
