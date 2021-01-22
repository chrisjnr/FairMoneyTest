object Libs{

    object Retrofit{
        private const val version = "2.6.0"
        private const val gsonVersion = "2.6.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }
    object OkHttp {
        private const val version = "3.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Kotlin {
        private const val version = "1.4.0"
        private const val coreVersion = "1.3.2"
        private const val coroutinesVersion = "1.3.2"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val core = "androidx.core:core-ktx:$coreVersion"
        const val coroutines =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"


    }

    object Koin{
        private const val version = "2.1.6"

        const val koin_core =  "org.koin:koin-core:$version"
        // Testing
        const val koin_test =  "org.koin:koin-test:$version"

        const val koin_android_viewmodel =  "org.koin:koin-android-viewmodel:$version"
    }


    object LiveData{
        private const val version = "2.2.0"

        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:$version"
    }

    object Navigation{
        val minSdkVersion = 21
        val daggerVersion = "2.12"
        val glideVersion = "3.8.0"
        val gsonVersion = "2.8.2"
        val okHttpVersion = "2.7.5'"
        val okHttp3Version = "3.9.0"
        val retrofit2Version = "2.6.0"
        val rxJavaVersion = "1.2.10"
        val rxAndroidVersion = "1.2.1"

        val koin_version = "2.1.6"
        val nav_version = "2.3.0"
        private const val version = "2.3.0"

        const val navigation = "androidx.navigation:navigation-fragment-ktx:$version"
        const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:$version"
        const val navigation_fragment = "androidx.navigation:navigation-fragment:$version"
        const val navigation_ui = "androidx.navigation:navigation-ui:$version"
    }



    object Android{
        private const val appCompatVersion = "1.2.0"
        private const val materialVersion = "1.2.1"

        private const val espressoVersion = "3.3.0"

        private const val gradleBuildToolsVersion = "gradle:4.1.0"

        private const val legacy_support = "1.0.0"

        const val material = "com.google.android.material:material:$materialVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.3"

        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"

        const val gradleBuildTools = "com.android.tools.build:$gradleBuildToolsVersion"

        const val activity = "androidx.legacy:legacy-support-v4:$legacy_support"

    }

    object JUnit{
        private const val version = "1.1.2"
        private const val jUnitVersion = "junit:4.12"
        const val junit = "androidx.test.ext:junit:$version"
        const val junit4 = "junit:$jUnitVersion"
    }


    object Gson{
        private const val version = "2.8.2"
        const val gson = "com.google.code.gson:gson:$version"
    }

    object Glide{
        private const val version ="3.8.0"
        const val glide = "com.github.bumptech.glide:glide:$version"

    }

    object Rx{
        private const val rxJavaAdapterVersion = "2.7.1"
        private const val rxJavaVersion = "2.2.19"
        private const val rxAndroidVersion = "2.0.1"

        const val adapter  = "com.squareup.retrofit2:adapter-rxjava2:$rxJavaAdapterVersion"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
        const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    }





}