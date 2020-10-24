package com.android.sample.githubclient.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.githubclient.R
import com.android.sample.githubclient.model.GithubRepositoryModel
import com.android.sample.githubclient.view.adapter.diff.GithubRepositoryDiffCallback
import com.android.sample.githubclient.view.adapter.holder.GithubRepositoryItemHolder
import kotlinx.coroutines.*

class GithubRepositoryAdapter(private var repositories: List<GithubRepositoryModel>) : RecyclerView.Adapter<GithubRepositoryItemHolder>() {
    interface OnGithubRepositoryClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnGithubRepositoryClickListener? = null

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github_repository, parent, false)
        return GithubRepositoryItemHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GithubRepositoryItemHolder, position: Int) {
        holder.bind(repositories[position])
    }

    fun update(updated : List<GithubRepositoryModel>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }
            repositories = updated
            diffResult.await().dispatchUpdatesTo(this@GithubRepositoryAdapter)
        }
    }

    private fun getDiffResult(updated: List<GithubRepositoryModel>): DiffUtil.DiffResult {
        val diffCallback = GithubRepositoryDiffCallback(repositories, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }

    fun getItem(position: Int) = repositories[position]
}