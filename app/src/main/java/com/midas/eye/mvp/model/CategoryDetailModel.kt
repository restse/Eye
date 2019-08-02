package com.midas.eye.mvp.model

import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.net.RetrofitManager
import com.midas.eye.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by midas on 2019/05/29.
 * desc: 分类详情的 Model
 */
class CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getCategoryDetailList(id)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多数据
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}