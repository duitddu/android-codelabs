package com.duit.android.codelabs.di

import android.app.Application
import com.duit.android.codelabs.di.koin.dataSourceModule
import com.duit.android.codelabs.di.koin.repositoryModule
import com.duit.android.codelabs.di.koin.viewModelModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule
                )
            )
        }
    }
}