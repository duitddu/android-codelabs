package com.android.sample.mytodo.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.android.sample.mytodo.database.TodoDAO
import com.android.sample.mytodo.database.TodoDatabase
import com.android.sample.mytodo.model.TodoModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TodoRepository(application: Application) {
    private var mTodoDatabase: TodoDatabase
    private var mTodoDAO: TodoDAO
    private var mTodoItems: LiveData<List<TodoModel>>

    init {
        mTodoDatabase = TodoDatabase.getInstance(application)
        mTodoDAO = mTodoDatabase.todoDao()
        mTodoItems = mTodoDAO.getTodoList()
    }

    fun getTodoList(): LiveData<List<TodoModel>> {
        return mTodoItems
    }

    fun insertTodo(todoModel: TodoModel) {
        Observable.just(todoModel)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mTodoDAO.insertTodo(todoModel)
            }, {
                // Handle error.
            })
    }

    fun updateTodo(todoModel: TodoModel) {
        Observable.just(todoModel)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mTodoDAO.updateTodo(todoModel)
            }, {
                // Handle error.
            })
    }

    fun deleteTodo(todoModel: TodoModel) {
        Observable.just(todoModel)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mTodoDAO.deleteTodo(todoModel)
            }, {
                // Handle error.
            })
    }
}