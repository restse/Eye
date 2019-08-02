package com.midas.eye.mvp.presenter

import com.midas.eye.base.BasePresenter
import com.midas.eye.mvp.contract.FollowContract
import com.midas.eye.mvp.model.FollowModel
import com.midas.eye.net.exception.ExceptionHandle

/**
 * Created by midas on 2019/05/28.
 * desc: 获取 TabInfo Presenter
 */
class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl: String? = null

    /**
     *  请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = followModel.requestFollowList()
            .subscribe({ issue ->
                mRootView?.apply {
                    dismissLoading()
                    nextPageUrl = issue.nextPageUrl
                    setFollowInfo(issue)
                }
            }, { throwable ->
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                .subscribe({ issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }

                }, { t ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })


        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}