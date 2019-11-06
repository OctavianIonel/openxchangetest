package com.octavianionel.openxchangetest.contracts

/**
 * Created by Reply on 06/11/2019.
 */
abstract class BasePresenter<T>(var view: T?) {

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    abstract fun detach()
}