package com.duitddu.android.codelabs.compose

import java.util.*

data class MyModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String = ""
)