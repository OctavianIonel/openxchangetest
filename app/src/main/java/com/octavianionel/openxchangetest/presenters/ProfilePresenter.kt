package com.octavianionel.openxchangetest.presenters

import com.octavianionel.openxchangetest.contracts.BasePresenter
import com.octavianionel.openxchangetest.contracts.ProfileContract

class ProfilePresenter(view: ProfileContract.TheView): BasePresenter<ProfileContract.TheView>(view), ProfileContract.Presenter {

    override fun detach() {
        view = null
    }

}