package com.midas.eye.mvp.presenter

import com.midas.eye.base.BasePresenter
import com.midas.eye.mvp.contract.HotTabContract
import com.midas.eye.mvp.model.HotTabModel
import com.midas.eye.net.exception.ExceptionHandle

/**
 * Created by midas on 2019/05/28.
 * desc: 获取 TabInfo Presenter
 */
class HotTabPresenter: BasePresenter<HotTabContract.View>(),HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }

    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
                .subscribe({
                    tabInfo->
                    mRootView?.setTabInfo(tabInfo)
                },{
                    throwable->
                    //处理异常
                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                })
        addSubscription(disposable)
    }
}