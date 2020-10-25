package com.duit.android.codelabs.di.hilt

import com.duit.android.codelabs.di.source.impl.LocalDataSourceImpl
import com.duit.android.codelabs.di.source.impl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {
    @Provides
    fun provideRemoteDataSource() = RemoteDataSourceImpl()
}
