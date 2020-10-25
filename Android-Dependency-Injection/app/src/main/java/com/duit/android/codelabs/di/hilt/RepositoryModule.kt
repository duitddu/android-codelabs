package com.duit.android.codelabs.di.hilt

import com.duit.android.codelabs.di.repository.DataRepository
import com.duit.android.codelabs.di.source.impl.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(dataSourceImpl: RemoteDataSourceImpl) =
        DataRepository(dataSourceImpl)
}