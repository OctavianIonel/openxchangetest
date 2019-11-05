package com.octavianionel.openxchangetest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.octavianionel.openxchangetest.R

/**
 * Created by Reply on 05/11/2019.
 */
class HomeFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        val TAG: String = HomeFragment::class.java.name

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }
}