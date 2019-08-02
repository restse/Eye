package com.midas.eye.base

/**
 * Created by midas on 2019/5/23
 * desc: Presenter 基类
 */


interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
