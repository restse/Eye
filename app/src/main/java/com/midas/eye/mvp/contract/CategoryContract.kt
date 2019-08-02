package com.midas.eye.mvp.contract

import com.midas.eye.base.IBaseView
import com.midas.eye.base.IPresenter
import com.midas.eye.mvp.model.bean.CategoryBean

/**
 * Created by midas on 2019/05/28.
 * desc: 分类 契约类
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }
}