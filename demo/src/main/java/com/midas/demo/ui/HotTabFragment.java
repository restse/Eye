package com.midas.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.midas.demo.R;
import com.midas.demo.base.BaseMvpFragment;
import com.midas.demo.mvp.contract.HotTabContract;
import com.midas.demo.mvp.model.bean.TabInfoBean;
import com.midas.demo.mvp.presenter.HotTabPresenter;

public class HotTabFragment extends BaseMvpFragment<HotTabPresenter> implements HotTabContract.View {

    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getTabInfo();
    }

//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_hot_tab;
//    }

//    @Override
//    protected void initView() {
//        textView =findView(R.id.tv);
//    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hot_tab, container,false);
        textView = view.findViewById(R.id.tv);
        return view;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected HotTabPresenter createPresenter() {
        return new HotTabPresenter();
    }

    @Override
    public void setTabInfo(TabInfoBean tabInfoBean) {
        textView.setText(tabInfoBean.toString());

    }

    @Override
    public void showError(String msg) {
        textView.setText(msg);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
