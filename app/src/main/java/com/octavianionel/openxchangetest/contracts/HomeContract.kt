package com.octavianionel.openxchangetest.contracts

/**
 * Created by Reply on 06/11/2019.
 */
interface HomeContract {

    interface TheView {

    }

    interface Presenter {
        fun downloadCsvFile(path: String): String
    }
}