package com.android.sample.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.mytodo.R
import com.android.sample.mytodo.databinding.ItemTodoBinding
import com.android.sample.mytodo.model.TodoModel
import java.text.SimpleDateFormat
import java.util.*

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

    fun setTodoItems(todoItems: List<TodoModel>) {
        this.todoItems = todoItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataBindingUtil.inflate<ItemTodoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_todo,
            parent,
            false
        ).let {
            TodoViewHolder(it, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TodoViewHolder)?.bind(todoItems.getOrNull(position) ?: return)
    }

    class TodoViewHolder(
        private val binding: ItemTodoBinding,
        listener: OnTodoItemClickListener?
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener?.onTodoItemClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                listener?.onTodoItemLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind(todoModel: TodoModel) {
            binding.model = todoModel
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("todoDate")
fun setTodoDate(tv: TextView, date: Long) {
    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    tv.text = simpleDateFormat.format(date)
}
