package com.duitddu.android.codelabs.compose.repository

import com.duitddu.android.codelabs.compose.MyModel

class FakeMyRepositoryImpl: MyRepository {
    override fun getModels(): List<MyModel> = listOf(
        MyModel(title = "Fake title 1"),
        MyModel(title = "Fake title 2"),
        MyModel(title = "Fake title 3"),
        MyModel(title = "Fake title 4"),
        MyModel(title = "Fake title 5"),
        MyModel(title = "Fake title 6"),
        MyModel(title = "Fake title 7"),
        MyModel(title = "Fake title 8"),
        MyModel(title = "Fake title 9"),
        MyModel(title = "Fake title 10")
    )
}