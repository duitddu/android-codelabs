package com.android.sample.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.sample.mytodo.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDAO

    companion object {
        @Volatile private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase = INSTANCE ?:
                synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TodoDatabase::class.java, "Todo.db").build()
    }
}