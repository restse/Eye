package com.midas.demo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.midas.demo.R
import com.midas.demo.base.BaseMvpActivity
import com.midas.demo.mvp.contract.DemoContract
import com.midas.demo.mvp.model.bean.CategoryBean
import com.midas.demo.mvp.presenter.DemoPresenter
import com.midas.demo.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_hot_tab.*


class DemoActivity : BaseMvpActivity<DemoPresenter>(), DemoContract.View {

    companion object {
        fun getInstance(context: Context) {
            val intent = Intent();
            intent.setClass(context, DemoActivity::class.java);
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.getGirl()
    }

    override fun getLayoutId(): Int = R.layout.activity_demo

    override fun initView() {
    }

    override fun createPresenter(): DemoPresenter = DemoPresenter()

    override fun initData() {
    }

    /*   override fun setGirl(categoryList: ArrayList<CategoryBean>?) {


       }

       override fun showError(errorMsg: String?) {
       }*/
    override fun setGirl(categoryList: ArrayList<CategoryBean>) {
        tv?.text = categoryList.toString()
        tv.setOnClickListener { ToastUtils.showShortSafe("测试") }
        tv.setOnClickListener {
           // val intent = Intent(this, TestActivity::class.java)
            startActivity(Intent(this, TestActivity::class.java))
        }
    }

    override fun showError(errorMsg: String) {

    }

    override fun showLoading() {
    }


    override fun hideLoading() {
    }


}