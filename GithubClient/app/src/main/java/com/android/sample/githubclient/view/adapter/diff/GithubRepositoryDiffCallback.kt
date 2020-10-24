package com.android.sample.githubclient.view.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.android.sample.githubclient.model.GithubRepositoryModel

class GithubRepositoryDiffCallback(private val oldList: List<GithubRepositoryModel>, private val newList: List<GithubRepositoryModel>) : DiffUtil.Callback() {

    override fun getNewListSize(): Int = newList.size
    override fun getOldListSize(): Int = oldList.size
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id
}