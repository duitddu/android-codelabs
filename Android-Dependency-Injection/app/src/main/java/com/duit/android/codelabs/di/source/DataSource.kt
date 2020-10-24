package com.duit.android.codelabs.di.source

import com.duit.android.codelabs.di.model.Data

interface DataSource {
    fun loadDataList(): List<Data>?
    fun saveData(data: Data): Boolean
}