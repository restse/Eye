package com.midas.demo.mvp.presenter;

import com.midas.demo.base.BasePresenter;
import com.midas.demo.mvp.contract.HotTabContract;
import com.midas.demo.mvp.model.HotTabModel;
import com.midas.demo.mvp.model.bean.TabInfoBean;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HotTabPresenter extends BasePresenter<HotTabContract.View> implements HotTabContract.presenter {

    private HotTabModel model = new HotTabModel();

    @Override
    public void getTabInfo() {
        checkViewAttached();
        mViewRef.get().showLoading();

        Disposable disposable = model.getTabInfo().subscribe(new Consumer<TabInfoBean>() {
            @Override
            public void accept(TabInfoBean tabInfoBean) throws Exception {
                mViewRef.get().setTabInfo(tabInfoBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mViewRef.get().showError("error");

            }
        });
        mViewRef.get().hideLoading();

        addSubscription(disposable);
    }
}
