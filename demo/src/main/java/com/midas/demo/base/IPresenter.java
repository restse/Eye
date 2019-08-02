package com.midas.demo.base;

/**
 * Created by midas on 2019/5/30
 * desc: 
 */

public interface IPresenter<V> {
    /**
     * 绑定 View
     */
    void attachView(V  mRootView);

    /**
     * 解除 View
     */
    void detachView();
}
