package com.midas.demo.mvp.model

import com.midas.demo.mvp.model.bean.CategoryBean
import com.midas.demo.net.RetrofitManger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DemoModel {

    /**
     * 获取妹纸
     */
    fun getGirl(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManger.getApiService().category
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}