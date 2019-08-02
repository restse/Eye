package com.midas.demo.base;

public interface IModel {

    void loadData(OnLoadListener OnLoadListener);

    //设计一个内部回调接口
    interface OnLoadListener<T> {
        void onComplete(T girls);
    }
}
