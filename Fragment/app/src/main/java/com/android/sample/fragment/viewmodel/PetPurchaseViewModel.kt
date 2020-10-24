package com.android.sample.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sample.fragment.data.PetPurchaseModel
import com.android.sample.fragment.data.PetType

class PetPurchaseViewModel : ViewModel() {
    private val _petPurchaseModel = MutableLiveData<PetPurchaseModel>(PetPurchaseModel())
    val petPurchaseModel: LiveData<PetPurchaseModel>
        get() = _petPurchaseModel

    fun purchase(petType: PetType) {
        _petPurchaseModel.value?.run {
            when(petType) {
                PetType.Dog -> dogCount += 1
                PetType.Cat -> catCount += 1
                PetType.Rabbit -> rabbitCount += 1
            }
            _petPurchaseModel.postValue(this)
        }
    }
}