package com.duit.android.codelabs.di.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.duit.android.codelabs.di.R
import com.duit.android.codelabs.di.main.adapter.DataListAdapter
import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.repository.DataRepository
import com.duit.android.codelabs.di.source.impl.LocalDataSourceImpl
import com.duit.android.codelabs.di.source.impl.RemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isRemote: Boolean = false
    private lateinit var dataListAdapter: DataListAdapter
    private lateinit var viewModel: MainViewModel

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
        val dataSource = if (isRemote) {
            RemoteDataSourceImpl()
        } else {
            LocalDataSourceImpl()
        }

        val repository = DataRepository(dataSource)

        viewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        viewModel.dataList.observe(this) {
            Log.e("Datalist", "$it")
            dataListAdapter.dataList = it
        }

        viewModel.loadDataList()
    }
}