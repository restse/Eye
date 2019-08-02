package com.midas.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by midas on 2019/5/30
 * desc: BaseMvpFragment基类
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IView {

    protected P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter =createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * Create presenter p.
     */
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
