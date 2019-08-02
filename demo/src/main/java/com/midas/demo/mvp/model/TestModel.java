package com.midas.demo.mvp.model;

import com.midas.demo.mvp.model.bean.CategoryBean;
import com.midas.demo.net.RetrofitManger;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class TestModel {

    /**
     * 获取妹纸
     */
    public Observable<ArrayList<CategoryBean>> getGirl() {
        return RetrofitManger.getApiService().getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


//    @Override
//    public void loadData(OnLoadListener OnLoadListener) {
//
//        String girl = "刘亦菲";
//
//        OnLoadListener.onComplete(girl);
//    }
}
