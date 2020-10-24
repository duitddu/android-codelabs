package com.duit.android.codelabs.di.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duit.android.codelabs.di.R
import com.duit.android.codelabs.di.model.Data

class DataListAdapter: RecyclerView.Adapter<DataViewHolder>() {
    var dataList: List<Data> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataViewHolder(
            inflater.inflate(R.layout.item_data, parent, false)
        )
    }
}