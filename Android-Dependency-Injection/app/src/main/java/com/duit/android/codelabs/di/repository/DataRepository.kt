package com.duit.android.codelabs.di.repository

import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val dataSource: DataSource) {
    fun loadDataList() = dataSource.loadDataList()
    fun saveData(data: Data) = dataSource.saveData(data)
}