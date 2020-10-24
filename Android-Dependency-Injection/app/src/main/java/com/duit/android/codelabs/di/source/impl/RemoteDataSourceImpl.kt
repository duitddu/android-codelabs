package com.duit.android.codelabs.di.source.impl

import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.model.fakeRemoteDataList
import com.duit.android.codelabs.di.source.DataSource

class RemoteDataSourceImpl: DataSource {
    override fun loadDataList(): List<Data>? = fakeRemoteDataList
    override fun saveData(data: Data) = fakeRemoteDataList.add(data)
}