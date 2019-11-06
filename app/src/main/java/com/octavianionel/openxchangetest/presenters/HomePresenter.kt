package com.octavianionel.openxchangetest.presenters

import com.octavianionel.openxchangetest.contracts.BasePresenter
import com.octavianionel.openxchangetest.contracts.HomeContract

/**
 * Created by Reply on 06/11/2019.
 */
class HomePresenter(view: HomeContract.TheView): BasePresenter<HomeContract.TheView>(view), HomeContract.Presenter {

    override fun detach() {
        view = null
    }

    override fun downloadCsvFile(path: String): String {
        return ""
    }

}