plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.android.lint) apply false
}

detekt {
    ignoreFailures = true
    source.setFrom(
        files(
            "composeApp/src/commonMain/kotlin",
            "composeApp/src/androidMain/kotlin",
            "composeApp/src/iosMain/kotlin",
            "common/logger/src/",
            "common/mvi/src/",
            "common/utils/src/"
        )
    )
}

ktlint {
    version.set("1.3.1")
    android.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
}

tasks.named("runKtlintCheckOverKotlinScripts") {
    enabled = false
}