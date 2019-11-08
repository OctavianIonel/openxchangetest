package com.octavianionel.openxchangetest.contracts

abstract class BasePresenter<T>(var view: T?) {

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    abstract fun detach()
}