package com.octavianionel.openxchangetest.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.octavianionel.openxchangetest.R
import com.octavianionel.openxchangetest.contracts.ProfileContract

/**
 * Created by Reply on 05/11/2019.
 */
class ProfileFragment: Fragment(), ProfileContract.TheView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.name

        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            return fragment
        }
    }
}