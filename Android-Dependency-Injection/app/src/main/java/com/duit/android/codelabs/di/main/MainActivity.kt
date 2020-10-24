package com.duit.android.codelabs.di.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.duit.android.codelabs.di.R
import com.duit.android.codelabs.di.main.adapter.DataListAdapter
import com.duit.android.codelabs.di.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private var isRemote: Boolean = false
    private lateinit var dataListAdapter: DataListAdapter

    private val viewModel: MainViewModel by viewModel(qualifier = named(if (isRemote) {
        "Remote"
    } else {
        "Local"
    }))

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