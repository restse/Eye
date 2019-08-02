package com.midas.demo.api;

import com.midas.demo.mvp.model.bean.AuthorInfoBean;
import com.midas.demo.mvp.model.bean.CategoryBean;
import com.midas.demo.mvp.model.bean.HomeBean;
import com.midas.demo.mvp.model.bean.TabInfoBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.util.ArrayList;

/**
 * Created by midas on 2019/5/31
 * desc: Api 接口
 */

public interface ApiService {

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    Observable<HomeBean> getFirstHomeData(@Query("num") int num);

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    Observable<HomeBean> getMoreHomeData(@Url String url);

    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    Observable<HomeBean.Issue> getRelatedData(@Query("id") Long id);

    /**
     * 获取分类
     */
    @GET("v4/categories")
    Observable<ArrayList<CategoryBean>> getCategory();

    /**
     * 获取分类详情List
     */
    @GET("v4/categories/videoList?")
    Observable<HomeBean.Issue> getCategoryDetailList(@Query("id") Long id);
    /**
     * 获取更多的 Issue
     */
    @GET
    Observable<HomeBean.Issue> getIssueData(@Url String url);

    /**
     * 获取全部排行榜的Info（包括，title 和 Url）
     */
    @GET("v4/rankList")
    Observable<TabInfoBean> getRankList();

    /**
     * 获取搜索信息
     */
    @GET("v1/search?&num=10&start=10")
    Observable<HomeBean.Issue> getSearchData(@Query("query") String query);

    /**
     * 热门搜索词
     */
    @GET("v3/queries/hot")
    Observable<ArrayList<String>> getHotWord();

    /**
     * 关注
     */
    @GET("v4/tabs/follow")
    Observable<HomeBean.Issue> getFollowInfo();

    /**
     * 作者信息
     */
    @GET("v4/pgcs/detail/tab?")
    Observable<AuthorInfoBean> getAuthorInfo(@Query("id") Long id);


}
