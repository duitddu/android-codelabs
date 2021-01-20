package com.android.sample.mytodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sample.mytodo.R
import com.android.sample.mytodo.databinding.ActivityMainBinding
import com.android.sample.mytodo.model.TodoModel
import com.android.sample.mytodo.view.adapter.TodoListAdapter
import com.android.sample.mytodo.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import kotlinx.android.synthetic.main.item_todo.view.*
import java.util.Date
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var mTodoListAdater: TodoListAdapter
    private lateinit var mDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initDataBinding()
        initRecyclerView()
        initAddButton()

    }

    private fun initDataBinding() {
        mDataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = mTodoViewModel
        }
    }
    private fun initRecyclerView() {
        mTodoListAdater = TodoListAdapter().apply {
            listener = object: TodoListAdapter.OnTodoItemClickListener {
                override fun onTodoItemClick(position: Int) {
                    openModifyTodoDialog(getItem(position))
                }

                override fun onTodoItemLongClick(position: Int) {
                    openDeleteTodoDialog(getItem(position))
                }
            }
        }
        mDataBinding.rlTodoList.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mTodoListAdater
        }
    }

    private fun initAddButton() {
        btn_add_todo.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("추가하기")
            .setView(dialogView)
            .setPositiveButton("확인", { dialogInterface, i ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val createdDate = Date().time

                val todoModel = TodoModel(null, title, description, createdDate)
                mTodoViewModel.insertTodo(todoModel)
            })
            .setNegativeButton("취소", null)
            .create()
        dialog.show()
    }

    private fun openModifyTodoDialog(todoModel: TodoModel) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo, null)
        dialogView.et_todo_title.setText(todoModel.title)
        dialogView.et_todo_description.setText(todoModel.description)

        val dialog = AlertDialog.Builder(this)
            .setTitle("수정하기")
            .setView(dialogView)
            .setPositiveButton("확인", { dialogInterface, i ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()

                todoModel.description = description
                todoModel.title = title

                mTodoViewModel.updateTodo(todoModel)
            })
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }

    private fun openDeleteTodoDialog(todoModel: TodoModel) {

        val dialog = AlertDialog.Builder(this)
            .setTitle("삭제하기")
            .setMessage("확인을 누르면 삭제됩니다.")
            .setPositiveButton("확인", { dialogInterface, i ->
                mTodoViewModel.deleteTodo(todoModel)
            })
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }

    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)
//        mTodoViewModel.getTodoList().observe(this, Observer {
//            mTodoListAdater.setTodoItems(it)
//        })
    }
}
