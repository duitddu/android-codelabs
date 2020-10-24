package com.android.sample.bottomnavigationview
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.sample.bottomnavigationview.fragment.FragmentOne
import com.android.sample.bottomnavigationview.fragment.FragmentThree
import com.android.sample.bottomnavigationview.fragment.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentOne by lazy { FragmentOne() }
    private val fragmentTwo by lazy { FragmentTwo() }
    private val fragmentThree by lazy { FragmentThree() }

    private val fragments: List<Fragment> = listOf(
        fragmentOne, fragmentTwo, fragmentThree
    )

    private val pagerAdapter: MainViewPagerAdapter by lazy {
        MainViewPagerAdapter(this, fragments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
        initNavigationBar()
    }

    private fun initNavigationBar() {
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                val page = when(it.itemId) {
                    R.id.first -> 0
                    R.id.second -> 1
                    R.id.third -> 2
                    else -> 0
                }

                if (page != vp_main.currentItem) {
                    vp_main.currentItem = page
                }

                true
            }
            selectedItemId = R.id.first
        }
    }

    private fun initViewPager() {
        vp_main.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val navigation = when(position) {
                        0 -> R.id.first
                        1 -> R.id.second
                        2 -> R.id.third
                        else -> R.id.first
                    }

                    if (bnv_main.selectedItemId != navigation) {
                        bnv_main.selectedItemId = navigation
                    }
                }
            })
        }
    }
}
