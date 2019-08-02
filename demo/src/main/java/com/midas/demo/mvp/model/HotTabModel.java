package com.midas.demo.mvp.model;

import com.midas.demo.mvp.model.bean.TabInfoBean;
import com.midas.demo.net.RetrofitManger;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HotTabModel {

    /**
     * 获取 TabInfo
     */
    public Observable<TabInfoBean> getTabInfo() {
        return RetrofitManger.getApiService().getRankList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
