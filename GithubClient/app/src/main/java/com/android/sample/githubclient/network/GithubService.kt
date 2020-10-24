package com.android.sample.githubclient.network

import com.android.sample.githubclient.network.api.GithubApi

object GithubService {
    private const val GITHUB_URL = "http://api.github.com"

    val client = BaseService().getClient(GITHUB_URL)?.create(GithubApi::class.java)
}