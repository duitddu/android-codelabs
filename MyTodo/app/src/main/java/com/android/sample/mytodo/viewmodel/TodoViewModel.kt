package com.android.sample.mytodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.sample.mytodo.model.TodoModel
import com.android.sample.mytodo.repository.TodoRepository

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val mTodoRepository: TodoRepository
    private var mTodoItems: LiveData<List<TodoModel>>

    init {
        mTodoRepository = TodoRepository(application)
        mTodoItems = mTodoRepository.getTodoList()
    }

    fun insertTodo(todoModel: TodoModel) {
        mTodoRepository.insertTodo(todoModel)
    }

    fun updateTodo(todoModel: TodoModel) {
        mTodoRepository.updateTodo(todoModel)
    }

    fun deleteTodo(todoModel: TodoModel) {
        mTodoRepository.deleteTodo(todoModel)
    }

    fun getTodoList(): LiveData<List<TodoModel>> {
        return mTodoItems
    }
}