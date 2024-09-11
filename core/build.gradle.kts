import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()


    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(libs.bundles.koin)
            implementation(libs.bundles.datastore)
            implementation(libs.bundles.ktor)
            implementation(libs.result)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.kylix.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
