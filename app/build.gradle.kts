plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.haikal.carousellTest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.haikal.carousellTest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
    }
}

dependencies {
    implementation (project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(project(":core"))
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.retrofit)
    implementation ("androidx.cardview:cardview:1.0.0")
    // Mockito
    testImplementation ("org.mockito:mockito-core:5.5.0")
    androidTestImplementation ("org.mockito:mockito-android:5.5.0")

    // JUnit for unit testing
    testImplementation ("junit:junit:4.13.2")

    // Coroutines testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // AndroidX Test - Core testing libraries
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    // To run tests on the main thread
    androidTestImplementation ("androidx.test:core:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    }