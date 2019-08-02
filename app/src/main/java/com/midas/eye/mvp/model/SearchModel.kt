package com.midas.eye.mvp.model

import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.net.RetrofitManager
import com.midas.eye.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by midas on 2019/05/28.
 * desc: 搜索 Model
 */
class SearchModel {

    /**
     * 请求热门关键词的数据
     */
    fun requestHotWordData(): Observable<ArrayList<String>> {
        return RetrofitManager.service.getHotWord()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 搜索关键词返回的结果
     */
    fun getSearchResult(words: String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getSearchData(words)
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
