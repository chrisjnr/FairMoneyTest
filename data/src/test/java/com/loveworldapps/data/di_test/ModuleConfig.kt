package com.loveworldapps.data.di_test

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.loveworldapps.data.di.allRepositoryImplViewModules
import com.loveworldapps.data.di.appModuleImpl
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.remote.FairMoneyApi
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by manuelchris-ogar on 25/01/2021.
 */
fun configureTestAppComponent(baseApi: String)
        = listOf(
    allRepositoryImplViewModules,
    appModuleImpl,
    mockWebServerTestModule,
    configureNetworkModuleForTest(baseApi),
    configureLocalModuleTest(),




)

/**
 * Mock Server Module for tests
 * */
val mockWebServerTestModule = module {

    factory {
        MockWebServer()
    }


}

/**
 * This method Provides an override to the usual okhttp request by using the mock server url
* */
fun configureNetworkModuleForTest(baseApi: String)
        = module(override = true){
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    factory{ get<Retrofit>().create(FairMoneyApi::class.java) }
}

private fun provideDb(context: Context): UsersDatabase = UsersDatabase.getInstance(context)


fun configureLocalModuleTest() = module(override = true) {
    single {
        Room.inMemoryDatabaseBuilder(get(), UsersDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    single { provideDb(androidContext()) }
}
