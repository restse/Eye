package com.midas.demo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.lang.ref.WeakReference;

/**
 * Created by midas on 2019/5/30
 * desc:
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected WeakReference<V> mViewRef;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    //protected V mViewRef;
    //进行绑定
    @Override
    public void attachView(V mRootView) {
        mViewRef = new WeakReference<>(mRootView);
        //mViewRef  = mRootView;
    }

    //解绑
    public void detachView() {
        mViewRef.clear();
        // mViewRef = null;
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private boolean isViewAttached() {
        return mViewRef != null;
    }

    protected void checkViewAttached() {
        //  if (!isViewAttached()) throw new MvpViewNotAttachedException();
        if (!isViewAttached()) {
            throw new RuntimeException("Please call IPresenter.attachView(IBaseView) before "+ "requesting data to the IPresenter");
        }
    }

//
//    private class MvpViewNotAttachedException extends Throwable {
//        public MvpViewNotAttachedException(){
//
//        }
//    }
}
