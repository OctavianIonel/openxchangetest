package com.octavianionel.openxchangetest.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.octavianionel.openxchangetest.R
import com.octavianionel.openxchangetest.utils.Utils
import com.octavianionel.openxchangetest.views.fragments.HomeFragment
import com.octavianionel.openxchangetest.views.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mNavigationBottom: BottomNavigationView

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->


            when (item.itemId) {

                R.id.menu_item_home-> {
                    showFragment(Utils.FragmentType.HOME, null)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.menu_item_profile -> {
                    showFragment(Utils.FragmentType.PROFILE, null)
                    return@OnNavigationItemSelectedListener true
                }

            }
            return@OnNavigationItemSelectedListener false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationBottom = findViewById(R.id.navigationBottom)
        mNavigationBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        showFragment(Utils.FragmentType.HOME, null)
    }

    fun showFragment(fragmentType: Utils.FragmentType, params: String?) {
        var fragment: Fragment?

        when (fragmentType) {

            Utils.FragmentType.HOME -> {
                fragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment,
                    HomeFragment.TAG
                )
                    .commit()
            }

            Utils.FragmentType.PROFILE -> {
                fragment = ProfileFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment,
                    ProfileFragment.TAG
                )
                    .commit()
            }
        }
    }
}
