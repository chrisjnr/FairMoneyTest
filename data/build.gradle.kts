plugins {
    id ("com.android.library")
    id ("kotlin-android")
    kotlin( "kapt")
    id ("koin")

}

android {
    compileSdkVersion (30)
    buildToolsVersion ("30.0.2")
    defaultConfig {
        minSdkVersion (21)
        targetSdkVersion (30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions ("server")


    productFlavors{


        create("staging"){
            this.multiDexEnabled = true
            buildConfigField("String", "base_url",  "\"https://dummyapi.io/data/\"")
            buildConfigField("Boolean", "DEBUG",  "\"TRUE\"")
            buildConfigField("String", "api_key",  "\"6005788bb90772d5e25dd9c7\"")



        }


        create("live"){
            buildConfigField("String", "base_url",  "\"https://dummyapi.io/data/\"")
            buildConfigField("Boolean", "DEBUG",  "\"FALSE\"")
            buildConfigField("String", "api_key",  "\"6005788bb90772d5e25dd9c7\"")


        }




    }

    buildTypes {
        getByName("release"){
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
}

dependencies {

    implementation (fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation (Libs.Navigation.navigation)
    implementation (Libs.Navigation.navigation_ui)

    // Java language implementation
    implementation (Libs.Navigation.navigation_fragment)
    implementation ( Libs.Navigation.navigation_ui_ktx)




    implementation (Libs.Android.appCompat)
    implementation (Libs.Android.constraintLayout)
    implementation (Libs.Android.activity)
    testImplementation (Libs.JUnit.junit4)
    androidTestImplementation ( Libs.JUnit.junit)
    androidTestImplementation (Libs.Android.espresso)

    api (Libs.Gson.gson)
    api (Libs.OkHttp.okhttp)

    // Retrofit deps
    api (Libs.Gson.gson)
    api (Libs.OkHttp.loggingInterceptor)
    api (Libs.Retrofit.retrofit)
    api (Libs.Retrofit.gsonConverter)
    implementation (Libs.Kotlin.core)
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.20")


    implementation (Libs.Rx.adapter)
    implementation (Libs.Rx.rxAndroid)
    implementation (Libs.Rx.rxJava)

    // Room components
    implementation ("androidx.room:room-runtime:2.2.5")
    kapt ("androidx.room:room-compiler:2.2.5")
    implementation ("androidx.room:room-ktx:2.2.5")
    androidTestImplementation ("androidx.room:room-testing:2.2.5")

    implementation (project(":domain"))

    implementation ("android.arch.paging:runtime:1.0.1")

    // Koin for Kotlin apps
    implementation (Libs.Koin.koin_core)
    // Testing
    testImplementation (Libs.Koin.koin_test)

    implementation (Libs.Koin.koin_android_viewmodel)

    implementation("com.google.truth:truth:1.0.1")

    testImplementation("com.squareup.okhttp3:mockwebserver:3.9.0")

//    testImplementation (" org.assertj:assertj-core:1.7.1")

    testImplementation ("org.robolectric:robolectric:4.3.1")

    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")


    testImplementation ("org.hamcrest:hamcrest-all:1.3")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("org.robolectric:robolectric:4.3.1")


    androidTestImplementation ("androidx.test:runner:1.1.1")
    androidTestImplementation ("androidx.test:core:1.1.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.0")
    androidTestImplementation ("androidx.test:rules:1.1.1")

    //Mockk
    testImplementation ("io.mockk:mockk:1.10.5")
//Koin Test
//    androidTestImplementation ("org.koin:koin-test:$koin_version")








}