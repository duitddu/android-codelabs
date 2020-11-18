package com.duitddu.android.codelabs.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duitddu.android.codelabs.compose.MyModel
import com.duitddu.android.codelabs.compose.repository.MyRepository

class MainViewModel(
    private val repository: MyRepository
) : ViewModel() {
    private val _myModels: MutableLiveData<List<MyModel>> = MutableLiveData()
    val myModels: LiveData<List<MyModel>> = _myModels

    val onItemClickEvent: MutableLiveData<MyModel> = MutableLiveData()

    fun loadMyModels() {
        repository.getModels().let {
            _myModels.postValue(it)
        }
    }

    fun onItemClick(position: Int) {
        _myModels.value?.getOrNull(position)?.let {
            onItemClickEvent.postValue(it)
        }
    }
}

class MainViewModelFactory(val repository: MyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class :: ${modelClass::class.java.simpleName}")
    }
}