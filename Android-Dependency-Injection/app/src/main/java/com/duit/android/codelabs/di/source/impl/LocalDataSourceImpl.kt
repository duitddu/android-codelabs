package com.duit.android.codelabs.di.source.impl

import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.model.fakeLocalDataList
import com.duit.android.codelabs.di.source.DataSource

class LocalDataSourceImpl: DataSource {
    override fun loadDataList(): List<Data>? = fakeLocalDataList
    override fun saveData(data: Data) = fakeLocalDataList.add(data)
}