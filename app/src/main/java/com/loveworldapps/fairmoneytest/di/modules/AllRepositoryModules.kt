package com.loveworldapps.fairmoneytest.di.modules

import com.loveworldapps.fairmoneytest.repository.UserRepository
import org.koin.dsl.module

/**
 * Created by manuelchris-ogar on 17/01/2020.
 */
val allRepositoryViewModules = module{
    single { UserRepository(get(),get()) }

}