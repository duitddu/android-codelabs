package com.duit.android.codelabs.di.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.duit.android.codelabs.di.model.Data
import kotlinx.android.synthetic.main.item_data.view.*

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dataTextView = itemView.tv_data

    fun bind(data: Data) {
        dataTextView.text = data.data
    }
}