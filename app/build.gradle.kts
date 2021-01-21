plugins{
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-android-extensions")
    id ("androidx.navigation.safeargs.kotlin")
    kotlin( "kapt")
}

android {
    compileSdkVersion (30)
    buildToolsVersion ("30.0.2")
    defaultConfig {
        applicationId = "com.loveworldapps.fairmoneytest"
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
            applicationIdSuffix = ".staging"
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
        getByName("debug"){
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

    viewBinding{
        this.isEnabled = true
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
    testImplementation (Libs.JUnit.junit4)
    androidTestImplementation ( Libs.JUnit.junit)
    androidTestImplementation (Libs.Android.espresso)


    implementation (Libs.Glide.glide)


    api (Libs.Gson.gson)
    api (Libs.OkHttp.okhttp)

    // Retrofit deps
    api (Libs.Gson.gson)
    api (Libs.OkHttp.loggingInterceptor)
    api (Libs.Retrofit.retrofit)
    api (Libs.Retrofit.gsonConverter)
    implementation (Libs.Kotlin.core)
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$rootProject.kotlin_version")

    implementation ("androidx.cardview:cardview:1.0.0")

    implementation (Libs.Android.material)
    implementation ("com.poovam:pin-edittext-field:1.2.3")

    implementation (Libs.LiveData.lifecycle_viewmodel)
    implementation (Libs.LiveData.lifecycle_livedata_ktx)
    implementation (Libs.LiveData.lifecycle_common_java8)
    implementation ("com.jakewharton.timber:timber:4.7.1")

    implementation ("androidx.activity:activity-ktx:1.1.0")


    // Koin for Kotlin apps
    implementation (Libs.Koin.koin_core)
    // Testing
    testImplementation (Libs.Koin.koin_test)

    implementation (Libs.Koin.koin_android_viewmodel)

    implementation (Libs.Kotlin.coroutines)

    implementation ("joda-time:joda-time:2.8.2")

    implementation (Libs.Rx.adapter)
    implementation (Libs.Rx.rxAndroid)
    implementation (Libs.Rx.rxJava)


    implementation ("android.arch.paging:runtime:1.0.1")

    implementation( "de.hdodenhof:circleimageview:3.1.0")



    // Room components
    implementation ("androidx.room:room-runtime:2.2.5")
    kapt ("androidx.room:room-compiler:2.2.5")
    implementation ("androidx.room:room-ktx:2.2.5")
    androidTestImplementation ("androidx.room:room-testing:2.2.5")


    //    Picasso
    implementation ("com.squareup.picasso:picasso:2.71828")


    implementation ("cn.pedant.sweetalert:library:1.3")


    implementation ("com.github.chnouman:AwesomeDialog:1.0.5")



}