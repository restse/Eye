package com.midas.eye.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.midas.eye.Constants
import com.midas.eye.R
import com.midas.eye.durationFormat
import com.midas.eye.glide.GlideApp
import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.ui.activity.VideoDetailActivity
import com.midas.eye.view.recyclerview.ViewHolder
import com.midas.eye.view.recyclerview.adapter.CommonAdapter
import android.support.v4.util.Pair

/**
 * Created by midas on 2019/05/28.
 * desc:分类详情Adapter
 */
class CategoryDetailAdapter(context: Context, dataList: ArrayList<HomeBean.Issue.Item>, layoutId: Int)
    : CommonAdapter<HomeBean.Issue.Item>(context, dataList, layoutId) {


    fun addData(dataList: ArrayList<HomeBean.Issue.Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }


    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        setVideoItem(holder, data)
    }

    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: ViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data
        val cover = itemData?.cover?.feed?:""
        // 加载封页图
        GlideApp.with(mContext)
                .load(cover)
                .apply(RequestOptions().placeholder(R.drawable.placeholder_banner))
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_image))
        holder.setText(R.id.tv_title, itemData?.title?:"")

        // 格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        holder.setText(R.id.tv_tag, "#${itemData?.category}/$timeFormat")

        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_image), item)
        })


    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra(VideoDetailActivity.Companion.TRANSITION, true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }

}