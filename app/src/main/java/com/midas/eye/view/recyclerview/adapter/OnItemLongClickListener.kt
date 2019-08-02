package com.midas.eye.view.recyclerview.adapter

/**
 * Created by midas on 2019/05/28.
 * Description: Adapter条目的长按事件
 */
interface OnItemLongClickListener {

    fun onItemLongClick(obj: Any?, position: Int): Boolean

}
