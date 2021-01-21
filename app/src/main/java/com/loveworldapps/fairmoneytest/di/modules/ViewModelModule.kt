package com.loveworldapps.fairmoneytest.di.modules

import com.loveworldapps.fairmoneytest.ui.UserViewModel
import com.loveworldapps.fairmoneytest.ui.UserViewModelFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by manuelchris-ogar on 19/01/2021.
 */
val  ViewModelModule = module {
    single { UserViewModel(get()) }
}