package com.midas.eye.mvp.contract

import com.midas.eye.base.IBaseView
import com.midas.eye.base.IPresenter
import com.midas.eye.mvp.model.bean.TabInfoBean

/**
 * Created by midas on 2019/05/28.
 * desc: 契约类
 */
interface HotTabContract {

    interface View: IBaseView {
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }
}