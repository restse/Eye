package com.midas.demo.mvp.contract;

import com.midas.demo.base.IPresenter;
import com.midas.demo.base.IView;
import com.midas.demo.mvp.model.bean.CategoryBean;

import java.util.ArrayList;

/**
 * Created by midas on 2019/5/30
 * desc: Test契约
 */

public interface TestContract {

     interface View extends IView {
        //显示妹纸
        void setGirl(ArrayList<CategoryBean> categoryList);

        void showError(String errorMsg);
    }

     interface Presenter extends IPresenter<View> {
        //获取妹纸
        void getGirl();
    }
}
