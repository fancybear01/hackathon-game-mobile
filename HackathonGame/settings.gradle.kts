rootProject.name = "HackathonGame"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// APP
include(":composeApp")

// COMMON
include(":common:mvi:mvi-general")
include(":common:mvi:mvi-koin-voyager")
include(":common:logger")
include(":common:utils")

// FEATURES
include(":feature:main-screen:main-screen-api")
include(":feature:main-screen:main-screen-impl")
include(":feature:onboarding:onboarding-api")
include(":feature:onboarding:onboarding-impl")
include(":feature:rewards-screen:rewards-screen-api")
include(":feature:rewards-screen:rewards-screen-impl")
include(":feature:course-screen:course-screen-api")
include(":feature:course-screen:course-screen-impl")
include(":feature:quiz-screen:quiz-screen-api")
include(":feature:quiz-screen:quiz-screen-impl")
include(":feature:theory:theory-screen-impl")
include(":feature:theory:theory-screen-api")

// CORE
include(":core:network")
include(":core:recources")
include(":core:settings")

// COMPONENTS
include(":components:pet")
include(":components:study")