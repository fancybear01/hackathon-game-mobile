import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

compose.resources {
    generateResClass = never
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.logger)
            implementation(projects.common.mvi.mviGeneral)
            implementation(projects.common.mvi.mviKoinVoyager)
            implementation(projects.core.network)
            implementation(projects.core.recources)
            implementation(projects.core.settings)
            implementation(projects.feature.mainScreen.mainScreenApi)
            implementation(projects.feature.mainScreen.mainScreenImpl)
            implementation(projects.feature.onboarding.onboardingApi)
            implementation(projects.feature.onboarding.onboardingImpl)
            implementation(projects.feature.courseScreen.courseScreenImpl)
            implementation(projects.feature.quizScreen.quizScreenApi)
            implementation(projects.feature.quizScreen.quizScreenImpl)
            implementation(project(":feature:rewards-screen:rewards-screen-impl"))
            implementation(project(":feature:rewards-screen:rewards-screen-api"))
            implementation(projects.feature.theory.theoryScreenApi)
            implementation(projects.feature.theory.theoryScreenImpl)
            implementation(projects.components.pet)
            implementation(projects.components.study)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.navigator)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.koin.android.navigation)
        }
    }
}

android {
    namespace = "com.coding.game"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.coding.game"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

