rootProject.name = "BeuKMM"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
//enableFeaturePreview("GRADLE_METADATA")

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
        maven(url = "https://dl.bintray.com/netguru/maven/")
        maven(url = "https://jitpack.io")
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
        maven(url = "https://dl.bintray.com/netguru/maven/")
        maven(url = "https://jitpack.io")
    }
}

include(":composeApp")
include(":common")
include(":navigation")
include(":splash")
include(":onboard")
include(":core")
include(":auth")
include(":main")
include(":home")
include(":camera")
include(":profile")