plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.edominguez.moviedb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.edominguez.moviedb"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        debug {
            buildConfigField("String","AMBIENT", "\"DEBUG\"")
            buildConfigField("String","BASE_URL", "\"https://api.themoviedb.org\"")
        }

        release {
            isMinifyEnabled = false
            buildConfigField("String","AMBIENT", "\"PROD\"")
            buildConfigField("String","BASE_URL", "\"https://api.themoviedb.org\"")
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
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //    <-- ...............  Desing  ................. -->

    //---- Skeleton layouts
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //---- Text Responsive
    implementation ("com.intuit.sdp:sdp-android:1.0.6")


    //---- Material Desing
    implementation ("com.google.android.material:material:1.11.0")

    //    <-- ...............  Core  ................. -->

    //---- Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")

    //---- Locations
    implementation ("com.google.android.gms:play-services-location:21.2.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

    //---- RecyclerView
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //---- Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx")

    //---- Koin dependency Injector
    implementation ("io.insert-koin:koin-android:3.1.2")

    //    <-- ............... Services ................. -->

    //---- HTTP INTERCEPTOR
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")

    //---- Retrofit
    implementation ("com.squareup.retrofit2:converter-scalars:2.5.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //    <-- ............... Utils ................. -->

    //---- Joda time
    implementation ("net.danlew:android.joda:2.10.12.2")

    //---- Glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")

    //---- Permissions
    implementation ("com.github.fondesa:kpermissions:3.4.0")
    implementation ("com.github.fondesa:kpermissions-coroutines:3.4.0")

}