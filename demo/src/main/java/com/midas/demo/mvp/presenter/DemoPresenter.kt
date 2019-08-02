package com.midas.demo.mvp.presenter

import com.midas.demo.base.BasePresenter
import com.midas.demo.mvp.contract.DemoContract
import com.midas.demo.mvp.model.DemoModel

class DemoPresenter : BasePresenter<DemoContract.View>(), DemoContract.Presenter {

    private val model by lazy { DemoModel() }

    override fun getGirl() {
        checkViewAttached()
        mViewRef.get()?.showLoading()

        val disposable = model.getGirl()
            .subscribe({ categoryList -> mViewRef.get()!!.setGirl(categoryList) },
                { mViewRef.get()!!.showError("error") })
        addSubscription(disposable)

        mViewRef.get()!!.hideLoading()
    }


}