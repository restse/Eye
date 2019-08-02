package com.midas.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    /**
     * Create presenter p.
     */
    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
