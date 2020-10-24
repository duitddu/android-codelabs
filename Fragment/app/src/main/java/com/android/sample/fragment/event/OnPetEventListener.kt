package com.android.sample.fragment.event

import com.android.sample.fragment.data.PetType

interface OnPetEventListener {
    fun onPetPurchase(type: PetType)
}