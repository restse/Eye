package com.midas.eye.mvp.contract

import com.midas.eye.base.IBaseView
import com.midas.eye.base.IPresenter
import com.midas.eye.mvp.model.bean.HomeBean

/**
 * Created by midas on 2019/05/29.
 * desc: 分类详情契约类
 */
interface CategoryDetailContract {

    interface View: IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)
    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}