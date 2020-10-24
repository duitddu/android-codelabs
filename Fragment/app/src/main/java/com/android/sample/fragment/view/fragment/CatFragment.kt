package com.android.sample.fragment.view.fragment

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
import kotlinx.android.synthetic.main.frag_cat.view.*
import org.greenrobot.eventbus.EventBus

class CatFragment : Fragment(){

    var listener: OnPetEventListener? = null

    private lateinit var viewModel: PetPurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            viewModel = ViewModelProvider(this).get(PetPurchaseViewModel::class.java)
        }


        return inflater.inflate(R.layout.frag_cat, null).apply {
            bt_cat.setOnClickListener {
//                listener?.onPetPurchase(PetType.Cat)
//                EventBus.getDefault().post(PetPurchaseEvent(type = PetType.Cat))
                viewModel.purchase(PetType.Cat)
            }
        }
    }
}