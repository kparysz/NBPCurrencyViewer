plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.nbp.currency.viewer.feature.detail.ui"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    kotlin {
        jvmToolchain(21)
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.currencyDetails.domain)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.navigation.compose)

    implementation(libs.hilt.android)
    debugImplementation(libs.ui.tooling)
    ksp(libs.hilt.compiler)
}
