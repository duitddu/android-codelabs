package com.android.sample.mytodo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class TodoModel (
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "createdDate")
    var createdDate: Long
) {
    constructor(): this(null, "", "", -1)
}


