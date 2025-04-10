import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinter) apply true
}

allprojects {
    applyKotlinter()
}

fun Project.applyKotlinter() {
    plugins.apply("org.jmailen.kotlinter")
    configure<KotlinterExtension> {
        ignoreFormatFailures = false
        reporters = arrayOf("checkstyle", "plain")
    }
    tasks.withType(LintTask::class) {
        exclude { it.file.path.contains("generated/") }
        exclude("**/excluded/**")
    }
}