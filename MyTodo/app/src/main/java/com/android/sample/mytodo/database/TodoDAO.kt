package com.android.sample.mytodo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.sample.mytodo.model.TodoModel

@Dao
interface TodoDAO  {

    @Query("SELECT * from Todo ORDER BY createdDate ASC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert
    fun insertTodo(todoModel: TodoModel)

    @Update
    fun updateTodo(todoModel: TodoModel)

    @Delete
    fun deleteTodo(todoModel: TodoModel)
}