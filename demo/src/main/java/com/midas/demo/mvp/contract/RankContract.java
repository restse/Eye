package com.midas.demo.mvp.contract;

import com.midas.demo.base.IPresenter;
import com.midas.demo.base.IView;
import com.midas.demo.mvp.model.bean.HomeBean;

import java.util.List;

public interface RankContract {

    interface View extends IView{

        void setRankList(List<HomeBean.Issue.Item> itemList);

        void setError(String error);
    }

    interface Presenter extends IPresenter<View>{

        void getRankList(String url);
    }
}
