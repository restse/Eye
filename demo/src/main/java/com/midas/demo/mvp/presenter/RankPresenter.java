package com.midas.demo.mvp.presenter;

import com.midas.demo.base.BasePresenter;
import com.midas.demo.mvp.contract.RankContract;
import com.midas.demo.mvp.model.RankModel;
import com.midas.demo.mvp.model.bean.HomeBean;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RankPresenter extends BasePresenter<RankContract.View> implements RankContract.Presenter {

    private RankModel model = new RankModel();

    @Override
    public void getRankList(String url) {
        checkViewAttached();
        mViewRef.get().showLoading();
        Disposable disposable = model.getRankList(url)
                .subscribe(new Consumer<HomeBean.Issue>() {
                    @Override
                    public void accept(HomeBean.Issue issue) throws Exception {
                        mViewRef.get().setRankList(issue.getItemList());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewRef.get().setError("error");

                    }
                });
        addSubscription(disposable);

    }
}
