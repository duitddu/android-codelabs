package com.android.sample.githubclient.repository

import com.android.sample.githubclient.network.GithubService

class GithubRepository {
    private val githubClient= GithubService.client

    suspend fun getRepositories(query: String) = githubClient?.getRepositories(query)
}