package com.android.sample.githubclient.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sample.githubclient.R
import com.android.sample.githubclient.model.GithubRepositoryModel
import com.android.sample.githubclient.repository.GithubRepository
import com.android.sample.githubclient.view.adapter.GithubRepositoryAdapter
import com.android.sample.githubclient.view.adapter.decoration.GithubRepositoryItemDecoration
import com.android.sample.githubclient.viewmodel.MainViewModel
import com.android.sample.githubclient.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mGithubRepositoryAdapter: GithubRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()
        initViewModel()
    }

    private fun initButton() {
        searchButton.setOnClickListener { onSearchClick() }
    }

    private fun initViewModel() {
        viewModelFactory = MainViewModelFactory(GithubRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.githubRepositories.observe(this) {
            updateRepositories(it)
        }
    }

    private fun updateRepositories(repos: List<GithubRepositoryModel>) {
        if(::mGithubRepositoryAdapter.isInitialized) {
            mGithubRepositoryAdapter.update(repos)
        } else {
            mGithubRepositoryAdapter = GithubRepositoryAdapter(repos).apply {
                listener = object : GithubRepositoryAdapter.OnGithubRepositoryClickListener {
                    override fun onItemClick(position: Int) {
                        mGithubRepositoryAdapter.getItem(position).run {
                            openGithub(htmlUrl)
                        }
                    }
                }
            }

            githubReposView.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mGithubRepositoryAdapter
                addItemDecoration(GithubRepositoryItemDecoration(6, 6))
            }
        }
    }

    private fun openGithub(url: String) {
        try {
            val uri = Uri.parse(url)
            Intent(Intent.ACTION_VIEW, uri).run {
                startActivity(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun onSearchClick() {
        inputView.run {
            viewModel.requestGithubRepositories(inputView.text.toString())
            text.clear()
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}
