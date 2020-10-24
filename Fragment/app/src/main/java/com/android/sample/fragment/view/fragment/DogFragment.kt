package com.android.sample.fragment.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.sample.fragment.data.PetType
import com.android.sample.fragment.R
import com.android.sample.fragment.data.PetPurchaseEvent
import com.android.sample.fragment.event.OnPetEventListener
import com.android.sample.fragment.viewmodel.PetPurchaseViewModel
import kotlinx.android.synthetic.main.frag_dog.view.*
import org.greenrobot.eventbus.EventBus

class DogFragment : Fragment(){

//    var listener: OnPetEventListener? = null

    private lateinit var viewModel: PetPurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            viewModel = ViewModelProvider(this).get(PetPurchaseViewModel::class.java)
        }

        return inflater.inflate(R.layout.frag_dog, null).apply {
            bt_dog.setOnClickListener {
//                listener?.onPetPurchase(PetType.Dog)
//                EventBus.getDefault().post(PetPurchaseEvent(type = PetType.Dog))
                viewModel.purchase(PetType.Dog)
            }
        }
    }
}