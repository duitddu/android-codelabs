package com.android.sample.jettodo.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.sample.jettodo.fragment.DoneFragment
import com.android.sample.jettodo.fragment.InProgressFragment
import com.android.sample.jettodo.fragment.TodoFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.e("Pager adapter", "createFragment :: $position")
        return when(position) {
            0 -> TodoFragment.newInstance()
            1 -> InProgressFragment.newInstance()
            2 -> DoneFragment.newInstance()
            else -> throw IllegalStateException()
        }
    }


}