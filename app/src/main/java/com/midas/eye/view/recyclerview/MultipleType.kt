package com.midas.eye.view.recyclerview

/**
 * Created by midas on 2019/05/28..
 * desc: 多布局条目类型
 */

interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}
