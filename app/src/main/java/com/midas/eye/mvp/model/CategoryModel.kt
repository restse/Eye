package com.midas.eye.mvp.model

import com.midas.eye.mvp.model.bean.CategoryBean
import com.midas.eye.net.RetrofitManager
import com.midas.eye.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by midas on 2019/05/28.
 * desc: 分类数据模型
 */
class CategoryModel {

    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())
    }
}