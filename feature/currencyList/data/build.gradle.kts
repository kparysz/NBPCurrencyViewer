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
    defaultConfig {
        minSdk = 29
    }
    namespace = "com.nbp.currency.viewer.feature.list.data"
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
    implementation(projects.feature.currencyList.domain)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.assertj.core)
    testImplementation(libs.turbine)
    testImplementation(libs.kotlinx.coroutines.test)
}
