package com.octavianionel.openxchangetest.contracts

import android.content.Context
import com.octavianionel.openxchangetest.model.Earthquake
import java.util.ArrayList

/**
 * Created by Reply on 06/11/2019.
 */
interface HomeContract {

    interface TheView {
        fun getTheContext(): Context?
        fun finishedDownloadingCsv()
        fun finishedSavingToDb(myList: ArrayList<Earthquake>)
    }

    interface Presenter {
        fun downloadCsvFile(url: String)
        fun readSavedCsvFile(context: Context?)
    }
}