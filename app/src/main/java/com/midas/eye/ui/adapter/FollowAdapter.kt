package com.midas.eye.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import com.midas.eye.R
import com.midas.eye.glide.GlideApp
import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.view.recyclerview.MultipleType
import com.midas.eye.view.recyclerview.ViewHolder
import com.midas.eye.view.recyclerview.adapter.CommonAdapter

/**
 * Created by midas on 2019/05/28.
 * desc: 关注 adapter
 */
class FollowAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>) :
    CommonAdapter<HomeBean.Issue.Item>(context, dataList, object : MultipleType<HomeBean.Issue.Item> {
        override fun getLayoutId(item: HomeBean.Issue.Item, position: Int): Int {
            return when {
                item.type == "videoCollectionWithBrief" ->
                    R.layout.item_follow
                else ->
                    throw IllegalAccessException("Api 解析出错了，出现其他类型")
            }
        }
    }) {


    fun addData(dataList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }


    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {

        when {
            data.type == "videoCollectionWithBrief" -> setAuthorInfo(data, holder)
        }

    }


    /**
     * 加载作者信息
     */
    private fun setAuthorInfo(item: HomeBean.Issue.Item, holder: ViewHolder) {
        val headerData = item.data?.header
        /**
         * 加载作者头像
         */
        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.drawable.default_avatar).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))
            }

        })
        holder.setText(R.id.tv_title, headerData.title)
        holder.setText(R.id.tv_desc, headerData.description)

        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
        /**
         * 设置嵌套水平的 RecyclerView
         */
        recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = FollowHorizontalAdapter(mContext, item.data.itemList, R.layout.item_follow_horizontal)

    }

}