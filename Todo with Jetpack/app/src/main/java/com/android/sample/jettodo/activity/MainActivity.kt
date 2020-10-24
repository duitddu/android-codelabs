package com.android.sample.jettodo.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.android.sample.jettodo.R
import com.android.sample.jettodo.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
        initBottomNavigation()
        initFloatingActionButton()
    }

    private fun initViewPager() {
        mViewPagerAdapter = ViewPagerAdapter(this)

        vp_main.run {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mViewPagerAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position) {
                        0 -> bnv_main.selectedItemId = R.id.bottom_navi_todo
                        1 -> bnv_main.selectedItemId = R.id.bottom_navi_in_progress
                        2 -> bnv_main.selectedItemId = R.id.bottom_navi_done
                        else -> return
                    }
                }
            })
        }
    }

    private fun initBottomNavigation() {
        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_navi_todo -> vp_main.setCurrentItem(0, true)
                R.id.bottom_navi_in_progress -> vp_main.setCurrentItem(1, true)
                R.id.bottom_navi_done -> vp_main.setCurrentItem(2, true)
                else -> {}
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun initFloatingActionButton() {
        fab_add_todo.setOnClickListener {
            Intent(this, EditTodoActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}
