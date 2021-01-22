package com.loveworldapps.data.di

import com.loveworldapps.data.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Created by manuelchris-ogar on 17/01/2020.
 */
val allRepositoryImplViewModules = module{
    single { UserRepositoryImpl(get(),get(), get()) }

}