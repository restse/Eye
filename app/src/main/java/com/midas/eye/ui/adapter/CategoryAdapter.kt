package com.midas.eye.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.midas.eye.Constants
import com.midas.eye.R
import com.midas.eye.api.MyApplication
import com.midas.eye.glide.GlideApp
import com.midas.eye.mvp.model.bean.CategoryBean
import com.midas.eye.ui.activity.CategoryDetailActivity
import com.midas.eye.view.recyclerview.ViewHolder
import com.midas.eye.view.recyclerview.adapter.CommonAdapter

/**
 * Created by midas on 2019/05/28.
 * desc: 分类的 Adapter
 */

class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {

    private var textTypeface:Typeface?=null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>){
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }
    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")
        //设置方正兰亭细黑简体
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category, object : ViewHolder.HolderImageLoader(data.bgPicture) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                        .load(path)
                        .placeholder(R.color.color_darker_gray)
                        .transition(DrawableTransitionOptions().crossFade())
                        .thumbnail(0.5f)
                        .into(iv)
            }
        })

        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, CategoryDetailActivity::class.java)
            intent.putExtra(Constants.BUNDLE_CATEGORY_DATA,data)
            mContext.startActivity(intent)
        })
    }
}
