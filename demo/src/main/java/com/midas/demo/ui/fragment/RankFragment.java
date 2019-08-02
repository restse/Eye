package com.midas.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.midas.demo.base.BaseMvpFragment;
import com.midas.demo.mvp.contract.RankContract;
import com.midas.demo.mvp.model.bean.HomeBean;
import com.midas.demo.mvp.presenter.RankPresenter;

import java.util.List;

public class RankFragment extends BaseMvpFragment<RankPresenter> implements RankContract.View {


    @Override
    protected RankPresenter createPresenter() {
        return new RankPresenter();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setRankList(List<HomeBean.Issue.Item> itemList) {

    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
