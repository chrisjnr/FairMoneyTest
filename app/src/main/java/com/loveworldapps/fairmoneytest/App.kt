package com.loveworldapps.fairmoneytest

import android.app.Application
import com.loveworldapps.data.di.allRepositoryImplViewModules
import com.loveworldapps.data.di.appModuleImpl
import com.loveworldapps.fairmoneytest.di.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by manuelchris-ogar on 18/01/2021.
 */
class App  : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf( allRepositoryImplViewModules, ViewModelModule, appModuleImpl))
        }


    }
}