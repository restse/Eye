package com.midas.demo.mvp.presenter;

import com.midas.demo.base.BasePresenter;
import com.midas.demo.mvp.contract.TestContract;
import com.midas.demo.mvp.model.TestModel;
import com.midas.demo.mvp.model.bean.CategoryBean;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;

public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    private TestModel model = new TestModel();

    /**
     * 获取妹纸
     */
    @Override
    public void getGirl() {
//        model.loadData(new IModel.OnLoadListener() {
//            @Override
//            public void onComplete(Object girls) {
//                mViewRef.get().showGirl(girls.toString());
//            }
//        });
        //mViewRef.get().setGirl(model.getGirl());
        checkViewAttached();

        mViewRef.get().showLoading();
        Disposable disposable = model.getGirl()
                .subscribe(new Consumer<ArrayList<CategoryBean>>() {
                    @Override
                    public void accept(ArrayList<CategoryBean> categoryList) throws Exception {
                        mViewRef.get().setGirl(categoryList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mViewRef.get().showError("error");
                    }
                });

        addSubscription(disposable);

        mViewRef.get().hideLoading();
    }


}
