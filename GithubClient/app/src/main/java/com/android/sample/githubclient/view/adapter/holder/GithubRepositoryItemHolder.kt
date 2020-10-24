package com.android.sample.githubclient.view.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.githubclient.extension.setImageWithGlide
import com.android.sample.githubclient.model.GithubRepositoryModel
import com.android.sample.githubclient.view.adapter.GithubRepositoryAdapter
import kotlinx.android.synthetic.main.item_github_repository.view.*

class GithubRepositoryItemHolder(view: View, listener: GithubRepositoryAdapter.OnGithubRepositoryClickListener?) : RecyclerView.ViewHolder(view) {
    private val avatarView: ImageView = view.avatarView
    private val fullNameView: TextView = view.fullNameView
    private val descriptionView: TextView = view.descriptionView
    private val starCountView: TextView = view.starCountView

    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: GithubRepositoryModel) {
        model.run {
            avatarView.setImageWithGlide(owner.avatarUrl)
            fullNameView.text = fullName
            descriptionView.text = description
            starCountView.text = "Stars : $stargazersCount"
        }
    }
}