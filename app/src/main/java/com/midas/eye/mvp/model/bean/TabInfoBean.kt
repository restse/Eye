package com.midas.eye.mvp.model.bean

/**
 * Created by midas on 2019/5/27
 * desc: 热门的 tabInfo
 */

data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
}
