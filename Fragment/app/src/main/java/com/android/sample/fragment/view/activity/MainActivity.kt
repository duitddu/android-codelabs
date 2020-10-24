package com.android.sample.fragment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.sample.fragment.data.PetType
import com.android.sample.fragment.R
import com.android.sample.fragment.data.PetPurchaseEvent
import com.android.sample.fragment.data.PetPurchaseModel
import com.android.sample.fragment.event.OnPetEventListener
import com.android.sample.fragment.view.fragment.CatFragment
import com.android.sample.fragment.view.fragment.DogFragment
import com.android.sample.fragment.view.fragment.RabbitFragment
import com.android.sample.fragment.viewmodel.PetPurchaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var dogFragment: DogFragment
    private lateinit var rabbitFragment: RabbitFragment
    private lateinit var catFragment: CatFragment

    private lateinit var viewModel: PetPurchaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initFragments()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(PetPurchaseViewModel::class.java)

        viewModel.petPurchaseModel.observe(this, Observer {
            updatePetCount(it)
        })
    }

    private fun initFragments() {
        dogFragment = DogFragment().apply {
//            this.listener = this@MainActivity
        }

        catFragment = CatFragment().apply {
//            this.listener = this@MainActivity
        }

        rabbitFragment = RabbitFragment()
            .apply {
//            this.listener = this@MainActivity
        }

        commitFragment(dogFragment, R.id.fl_frag_dog)
        commitFragment(catFragment, R.id.fl_frag_cat)
        commitFragment(rabbitFragment,
            R.id.fl_frag_rabbit
        )
    }

    private fun commitFragment(fragment: Fragment, containerResId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(containerResId, fragment)
            .commit()
    }

    private fun updatePetCount(model: PetPurchaseModel) {
        tv_pet_count.text = "강아지 : ${model.dogCount}\n" +
                "고양이 : ${model.catCount}\n" +
                "토끼 : ${model.rabbitCount}"
    }
}
