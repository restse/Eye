package com.midas.demo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.midas.demo.R;
import com.midas.demo.base.BaseMvpActivity;
import com.midas.demo.mvp.contract.TestContract;
import com.midas.demo.mvp.model.bean.CategoryBean;
import com.midas.demo.mvp.presenter.TestPresenter;

import java.util.ArrayList;

public class TestActivity extends BaseMvpActivity<TestPresenter> implements TestContract.View {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getGirl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        tv = findViewById(R.id.tv);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "加载中", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void setGirl(String girl) {
//        tv.setText(girl);
//    }

    @Override
    public void setGirl(ArrayList<CategoryBean> categoryList) {
        tv.setText(categoryList.toString());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(this,DemoActivity.class);
//                Intent intent = new Intent();
//                intent.setClass(TestActivity.this,DemoActivity.class);
//                startActivity(intent);
                DemoActivity.Companion.getInstance(TestActivity.this);
            }
        });
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, "加载错误" + errorMsg, Toast.LENGTH_SHORT).show();
    }
}
