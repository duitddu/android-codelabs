package com.android.sample.mytodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sample.mytodo.R
import com.android.sample.mytodo.databinding.ActivityMainBinding
import com.android.sample.mytodo.databinding.DialogAddTodoBinding
import com.android.sample.mytodo.model.TodoModel
import com.android.sample.mytodo.view.adapter.TodoListAdapter
import com.android.sample.mytodo.viewmodel.TodoViewModel
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var mTodoListAdapter: TodoListAdapter
    private lateinit var mDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
        initRecyclerView()
        initAddButton()
        observeViewModel()
    }

    private fun initDataBinding() {
        mDataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java).also {
            mDataBinding.viewModel = it
        }
    }

    private fun observeViewModel() {
        mTodoViewModel.getTodoList().observe(this, Observer {
            mTodoListAdapter.setTodoItems(it)
        })
    }

    private fun initRecyclerView() {
        mTodoListAdapter = TodoListAdapter().apply {
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
            adapter = mTodoListAdapter
        }
    }

    private fun initAddButton() {
        mDataBinding.btnAddTodo.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogView = DialogAddTodoBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setTitle("추가하기")
            .setView(dialogView.root)
            .setPositiveButton("확인") { _, _ ->
                val title = dialogView.etTodoTitle.text.toString()
                val description = dialogView.etTodoDescription.text.toString()
                val createdDate = Date().time

                val todoModel = TodoModel(null, title, description, createdDate)
                mTodoViewModel.insertTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()
        dialog.show()
    }

    private fun openModifyTodoDialog(todoModel: TodoModel) {
        val dialogView = DialogAddTodoBinding.inflate(LayoutInflater.from(this))
        dialogView.etTodoTitle.setText(todoModel.title)
        dialogView.etTodoDescription.setText(todoModel.description)

        val dialog = AlertDialog.Builder(this)
            .setTitle("수정하기")
            .setView(dialogView.root)
            .setPositiveButton("확인") { _, _ ->
                val title = dialogView.etTodoTitle.text.toString()
                val description = dialogView.etTodoDescription.text.toString()

                todoModel.description = description
                todoModel.title = title

                mTodoViewModel.updateTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }

    private fun openDeleteTodoDialog(todoModel: TodoModel) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("삭제하기")
            .setMessage("확인을 누르면 삭제됩니다.")
            .setPositiveButton("확인") { _, _ ->
                mTodoViewModel.deleteTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }
}
