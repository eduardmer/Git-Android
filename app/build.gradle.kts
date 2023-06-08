import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.gitandroid"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.gitandroid"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "CLIENT_ID", gradleLocalProperties(rootDir).getProperty("clientId"))
        buildConfigField("String", "CLIENT_SECRET", gradleLocalProperties(rootDir).getProperty("clientSecret"))
        buildConfigField("String", "REDIRECT_URI", gradleLocalProperties(rootDir).getProperty("redirectUri"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.browser)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.junit)

    implementation(libs.dagger.android)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.android.coil)
    implementation(project(":core-data"))
    implementation(project(":core-domain"))
    implementation(project(":core-model"))
}
