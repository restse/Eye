package com.midas.eye.mvp.model

import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.net.RetrofitManager
import com.midas.eye.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by midas on 2019/05/28.
 * desc: 排行榜 Model
 */
class RankModel {

    /**
     * 获取排行榜
     */
    fun requestRankList(apiUrl:String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(apiUrl)
                .compose(SchedulerUtils.ioToMain())
    }

}
