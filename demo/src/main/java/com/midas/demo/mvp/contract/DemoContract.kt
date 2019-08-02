package com.midas.demo.mvp.contract

import com.midas.demo.base.IPresenter
import com.midas.demo.base.IView
import com.midas.demo.mvp.model.bean.CategoryBean

interface DemoContract {

    interface View :IView{
        //显示妹纸
        fun setGirl(categoryList: ArrayList<CategoryBean>)

        fun showError(errorMsg: String)
    }

    interface Presenter: IPresenter<View>{
        //获取妹纸
        fun  getGirl()
    }
}