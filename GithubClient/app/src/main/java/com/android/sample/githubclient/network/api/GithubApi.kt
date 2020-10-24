package com.android.sample.githubclient.network.api

import com.android.sample.githubclient.model.GithubRepositoriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String
    ): Response<GithubRepositoriesModel>
}