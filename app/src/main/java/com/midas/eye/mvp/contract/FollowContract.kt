package com.midas.eye.mvp.contract

import com.midas.eye.base.IBaseView
import com.midas.eye.base.IPresenter
import com.midas.eye.mvp.model.bean.HomeBean

/**
 * Created by midas on 2019/05/28.
 * desc: 契约类
 */
interface FollowContract {

    interface View : IBaseView {
        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 获取List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }
}