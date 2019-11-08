package com.octavianionel.openxchangetest.views.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.octavianionel.openxchangetest.R
import com.octavianionel.openxchangetest.contracts.HomeContract
import com.octavianionel.openxchangetest.model.Earthquake
import com.octavianionel.openxchangetest.presenters.HomePresenter
import com.octavianionel.openxchangetest.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


/**
 * Created by Reply on 05/11/2019.
 */
class HomeFragment: Fragment(), HomeContract.TheView {

    private var mPresenter: HomePresenter? = null
    private var mActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = HomePresenter(this)
        mActivity = activity as Activity
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show_table_with_data_btn?.text = getString(R.string.write_permission_btn)
        show_results_tv?.text = ""
        show_table_with_data_btn?.setOnClickListener {
            show_results_tv?.text = getString(R.string.show_result_waiting)
            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE)
        }
    }

    // Function to check and request permission.
    fun checkPermission(permission: String, requestCode: Int) {
        context?.let {
            if (ContextCompat.checkSelfPermission(it, permission) == PackageManager.PERMISSION_DENIED) {
                // Requesting the permission
                ActivityCompat.requestPermissions(mActivity as Activity, arrayOf(permission, Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
            } else {
                show_table_with_data_btn?.text = getString(R.string.downloading_data_btn)
                mPresenter?.downloadCsvFile(Utils.LINK_M10_EARTHQUAKES)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                show_table_with_data_btn?.text = getString(R.string.downloading_data_btn)
                mPresenter?.downloadCsvFile(Utils.LINK_M10_EARTHQUAKES)
            }
        }
    }

    override fun getTheContext(): Context? {
        return context
    }

    override fun finishedDownloadingCsv() {
        show_table_with_data_btn?.text = getString(R.string.data_downloaded_btn)
        mPresenter?.readSavedCsvFile(getTheContext())
    }

    override fun finishedSavingToDb(myList: ArrayList<Earthquake>) {
        show_results_tv?.text = ""
        show_table_with_data_btn?.text = getString(R.string.data_loaded_from_db_btn)

        //for simplicity display only 3 items
        show_results_tv?.text = show_results_tv?.text.toString() + myList.get(0) + "\n\n"
        show_results_tv?.text = show_results_tv?.text.toString() + myList.get(1) + "\n\n"
        show_results_tv?.text = show_results_tv?.text.toString() + myList.get(2) + "\n\n"

    }


    companion object {
        val TAG: String = HomeFragment::class.java.name
        private val MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1234
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }
}