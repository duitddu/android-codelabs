package com.duitddu.android.codelabs.compose.repository

import com.duitddu.android.codelabs.compose.MyModel

interface MyRepository {
    fun getModels(): List<MyModel>
}