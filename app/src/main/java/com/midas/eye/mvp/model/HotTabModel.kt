package com.midas.eye.mvp.model

import com.midas.eye.mvp.model.bean.TabInfoBean
import com.midas.eye.net.RetrofitManager
import com.midas.eye.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by midas on 2019/05/28.
 * desc: 热门 Model
 */
class HotTabModel {

    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {
        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }

}
