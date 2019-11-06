package com.octavianionel.openxchangetest.presenters

import com.octavianionel.openxchangetest.contracts.BasePresenter
import com.octavianionel.openxchangetest.contracts.ProfileContract

/**
 * Created by Reply on 06/11/2019.
 */
class ProfilePresenter(view: ProfileContract.TheView): BasePresenter<ProfileContract.TheView>(view), ProfileContract.Presenter {

    override fun detach() {
        view = null
    }

}