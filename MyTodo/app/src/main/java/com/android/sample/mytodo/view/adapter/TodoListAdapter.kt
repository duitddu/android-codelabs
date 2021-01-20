package com.android.sample.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.mytodo.R
import com.android.sample.mytodo.model.TodoModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var todoItems: List<TodoModel> = listOf()

    interface OnTodoItemClickListener {
        fun onTodoItemClick(position: Int)
        fun onTodoItemLongClick(position: Int)
    }

    var listener: OnTodoItemClickListener? = null

    fun getItem(position: Int): TodoModel {
        return todoItems[position]
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val viewHolder = TodoViewHolder(view, listener)

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]

        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todoModel)
    }

    fun setTodoItems(todoItems: List<TodoModel>) {
        Observable.just(todoItems)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .map { DiffUtil.calculateDiff(TodoListDiffCallback(this.todoItems, todoItems)) }
            .subscribe({
                this.todoItems = todoItems
                it.dispatchUpdatesTo(this)
            }, {

            })
    }

    class TodoViewHolder(view: View, listener: OnTodoItemClickListener?): RecyclerView.ViewHolder(view) {
        val title = view.tv_todo_title
        val description = view.tv_todo_description
        val createdDate = view.tv_todo_created_date

        init {
            view.setOnClickListener {
                listener?.onTodoItemClick(adapterPosition)
            }

            view.setOnLongClickListener {
                listener?.onTodoItemLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind(todoModel: TodoModel) {
            title.text = todoModel.title
            description.text = todoModel.description
            createdDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }
}

fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}

