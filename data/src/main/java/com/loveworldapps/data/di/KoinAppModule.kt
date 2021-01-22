package com.loveworldapps.data.di

import android.content.Context
import com.loveworldapps.data.BuildConfig
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.UserEntityMapper
import com.loveworldapps.data.remote.FairMoneyApi
import com.loveworldapps.data.remote.config.AuthInterceptor
import com.loveworldapps.data.remote.config.ConnectivityCheckerInterceptor
import com.loveworldapps.data.remote.config.NetworkResponseAdapterFactory
import com.loveworldapps.domain.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by manuelchris-ogar on 18/01/2020.
 */
    private fun provideApiService(retrofit: Retrofit): FairMoneyApi = retrofit.create(FairMoneyApi::class.java)


    private fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BuildConfig.base_url)
            .client(okHttpClient)
            .build()


    private fun provideConnectivityCheckerInterceptor(context: Context): ConnectivityCheckerInterceptor = ConnectivityCheckerInterceptor(context)

    private fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    private fun provideOkHttpClient(connectivityCheckerInterceptor: ConnectivityCheckerInterceptor, authInterceptor: AuthInterceptor) = if (!BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectivityCheckerInterceptor)
                .addInterceptor(authInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .addInterceptor(connectivityCheckerInterceptor)
        .addInterceptor(authInterceptor)
        .build()


    fun provideMapper() = UserEntityMapper()

    private fun provideUser() = User("", "", "", "",  "", "")
    private fun provideDb(context: Context): UsersDatabase = UsersDatabase.getInstance(context)

    val appModuleImpl = module {
        single { provideConnectivityCheckerInterceptor(get()) }
        single { provideOkHttpClient(get(), get()) }
        single { provideRetrofit(get()) }
        single { provideApiService(get()) }
        single { provideDb(androidContext()) }
        single { provideUser() }
        single { provideAuthInterceptor() }
        single { provideMapper() }
    }
