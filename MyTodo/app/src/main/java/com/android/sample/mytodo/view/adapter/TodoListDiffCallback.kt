package com.android.sample.mytodo.view.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.android.sample.mytodo.model.TodoModel

class TodoListDiffCallback(val oldTodoList: List<TodoModel>, val newTodoList: List<TodoModel>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTodoList[oldItemPosition].id == newTodoList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldTodoList.size

    override fun getNewListSize(): Int = newTodoList.size

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.e("TAG", "areContentsTheSame ${oldTodoList[oldItemPosition]}")
        Log.e("TAG", "areContentsTheSame ${newTodoList[newItemPosition]}")
        return newTodoList[newItemPosition].equals(oldTodoList[oldItemPosition])
    }
}