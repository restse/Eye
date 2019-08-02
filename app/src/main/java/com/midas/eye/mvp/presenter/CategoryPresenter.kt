package com.midas.eye.mvp.presenter

import com.midas.eye.base.BasePresenter
import com.midas.eye.mvp.contract.CategoryContract
import com.midas.eye.mvp.model.CategoryModel
import com.midas.eye.net.exception.ExceptionHandle

/**
 * Created by midas on 2019/05/28.
 * desc:分类的 Presenter
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryData()
                .subscribe({ categoryList ->
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(categoryList)
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }

                })

        addSubscription(disposable)
    }
}