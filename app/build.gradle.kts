plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)

}

android {
    namespace = "com.bassem.catdemo"
    compileSdk = 34

    defaultConfig {
        multiDexEnabled = true
        applicationId = "com.bassem.catdemo"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
            create("benchmark") {
                initWith(getByName("release"))
                signingConfig = signingConfigs.getByName("debug")
                isDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules-benchmark.pro"
                )
            }
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    testOptions {
        packaging {
            resources.excludes.add("META-INF/*")
        }
        managedDevices {
            devices {
                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel2api27").apply {
                    device = "Pixel 2"
                    apiLevel = 27
                    systemImageSource = "aosp"
                }
            }
        }
        unitTests.isReturnDefaultValues = true

    }

}

dependencies {
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.ktx)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.glide)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Testing dependencies
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    implementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.androidx.core.testing.v210)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

}
