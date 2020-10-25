package com.duit.android.codelabs.di.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.duit.android.codelabs.di.R
import com.duit.android.codelabs.di.main.adapter.DataListAdapter
import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.repository.DataRepository
import com.duit.android.codelabs.di.source.impl.LocalDataSourceImpl
import com.duit.android.codelabs.di.source.impl.RemoteDataSourceImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataListAdapter: DataListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView() {
        dataListAdapter = DataListAdapter()
        rv_data_list.run {
            adapter = dataListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        bt_add_data.setOnClickListener {
            viewModel.saveData(Data("This is new added data"))
        }
    }

    private fun initViewModel() {
        viewModel.dataList.observe(this) {
            dataListAdapter.dataList = it
        }

        viewModel.loadDataList()
    }
}