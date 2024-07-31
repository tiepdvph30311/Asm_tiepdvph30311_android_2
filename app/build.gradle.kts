    plugins {
        alias(libs.plugins.androidApplication)

    }

    android {
        namespace = "tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2"
        compileSdk = 34

        defaultConfig {
            applicationId = "tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2"
            minSdk = 26
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    dependencies {
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        implementation(libs.room.runtime)

        implementation(libs.navigation.fragment)
        implementation(libs.navigation.ui)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        implementation(libs.room.runtime)
        annotationProcessor(libs.room.compiler) // Sử dụng annotationProcessor cho Room

        implementation(libs.navigation.fragment)
        implementation(libs.navigation.ui)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
    }
