package com.duit.android.codelabs.di.koin

import com.duit.android.codelabs.di.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(qualifier = named("Remote")) {
        MainViewModel(get(qualifier = named("Remote")))
    }

    viewModel(qualifier = named("Local")) {
        MainViewModel(get(qualifier = named("Local")))
    }
}