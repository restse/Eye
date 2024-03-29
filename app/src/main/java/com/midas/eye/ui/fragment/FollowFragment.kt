package com.midas.eye.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.midas.eye.R
import com.midas.eye.base.BaseFragment
import com.midas.eye.mvp.contract.FollowContract
import com.midas.eye.mvp.model.bean.HomeBean
import com.midas.eye.mvp.presenter.FollowPresenter
import com.midas.eye.net.exception.ErrorStatus
import com.midas.eye.showToast
import com.midas.eye.ui.adapter.FollowAdapter
import kotlinx.android.synthetic.main.layout_recyclerview.*

/**
 * Created by midas on 2019/05/24.
 * 关注
 */

class FollowFragment : BaseFragment(), FollowContract.View {

    private var mTitle: String? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private val mPresenter by lazy { FollowPresenter() }

    private val mFollowAdapter by lazy { activity?.let { FollowAdapter(it,itemList) } }

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    init {
        mPresenter.attachView(this)
    }

    companion object {
        fun getInstance(title: String): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_recyclerview


    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView.layoutManager?.itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (itemCount != null) {
                    if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                        loadingMore = true
                        mPresenter.loadMoreData()
                    }
                }
            }
        })

        mLayoutStatusView = multipleStatusView
    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
      multipleStatusView.showContent()
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter?.addData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
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
