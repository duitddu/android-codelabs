package com.android.sample.githubclient.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.sample.githubclient.repository.GithubRepository

class MainViewModelFactory(private val githubRepository: GithubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GithubRepository::class.java).newInstance(githubRepository)
    }
}