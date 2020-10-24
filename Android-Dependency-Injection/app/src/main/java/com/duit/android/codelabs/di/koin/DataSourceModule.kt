package com.duit.android.codelabs.di.koin

import com.duit.android.codelabs.di.source.impl.LocalDataSourceImpl
import com.duit.android.codelabs.di.source.impl.RemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single(qualifier = named("Remote")) {
        RemoteDataSourceImpl()
    }

    single(qualifier = named("Local")) {
        LocalDataSourceImpl()
    }
}