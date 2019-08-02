package com.midas.demo.mvp.contract;

import com.midas.demo.base.IPresenter;
import com.midas.demo.base.IView;
import com.midas.demo.mvp.model.bean.TabInfoBean;

public interface HotTabContract {

    interface View extends IView{
        /**
         * 设置 TabInfo
         */
        void setTabInfo(TabInfoBean tabInfoBean);

        void showError(String msg);
    }
    interface presenter extends IPresenter<View>{
        /**
         * 获取 TabInfo
         */
        void getTabInfo();
    }
}
