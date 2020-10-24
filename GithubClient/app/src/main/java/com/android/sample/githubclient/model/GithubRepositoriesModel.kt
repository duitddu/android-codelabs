package com.android.sample.githubclient.model

import com.google.gson.annotations.SerializedName

data class GithubRepositoriesModel(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<GithubRepositoryModel>
)