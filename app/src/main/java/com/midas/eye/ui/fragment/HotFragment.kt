package com.midas.eye.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.midas.eye.R
import com.midas.eye.base.BaseFragment
import com.midas.eye.base.BaseFragmentAdapter
import com.midas.eye.mvp.contract.HotTabContract
import com.midas.eye.mvp.model.bean.TabInfoBean
import com.midas.eye.mvp.presenter.HotTabPresenter
import com.midas.eye.net.exception.ErrorStatus
import com.midas.eye.showToast
import com.midas.eye.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*
/**
 * Created by midas on 2019/05/28.
 * 热门
 */
class HotFragment : BaseFragment(), HotTabContract.View {

    private val mPresenter by lazy { HotTabPresenter() }

    private var mTitle: String? = null
    /**
     * 存放 tab 标题
     */
    private val mTabTitleList = ArrayList<String>()
    private val mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        //状态栏透明处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPadding(it, toolbar) }
    }

    override fun lazyLoad() {
       mPresenter.getTabInfo()
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {

    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {
        multipleStatusView.showContent()

        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun showError(errorMsg: String,errorCode:Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}