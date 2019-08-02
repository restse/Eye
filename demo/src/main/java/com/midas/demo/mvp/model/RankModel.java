package com.midas.demo.mvp.model;

import com.midas.demo.mvp.model.bean.HomeBean;
import com.midas.demo.net.RetrofitManger;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RankModel {

    public Observable<HomeBean.Issue> getRankList(String url){
        return RetrofitManger.getApiService()
                .getIssueData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
