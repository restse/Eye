package com.midas.demo.net;

import com.midas.demo.api.ApiService;
import com.midas.demo.api.MyApplication;
import com.midas.demo.api.UrlConstant;
import com.midas.demo.utils.AppUtils;
import com.midas.demo.utils.NetUtil;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by midas on 2019/5/31
 * desc:
 */

public class RetrofitManger {

    private Retrofit retrofit;
    private static ApiService mApiService;

    //设缓存有效期为1天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;


    public void initOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(queryParameterInterceptor)  //参数添加
                .addInterceptor(headerInterceptor) // token过滤
                .addInterceptor(cacheInterceptor)
                .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    /**
     * 单一实例
     */
    private RetrofitManger() {
    }

    public static RetrofitManger getInstance() {
        return SingleInstance.INSTANCE;
    }

    private static class SingleInstance {
        private static final RetrofitManger INSTANCE = new RetrofitManger();
    }

    /**
     * Gets api service.
     *
     * @return the api service
     */
    public static ApiService getApiService() {
        return mApiService;
    }

    /**
     * 设置公共参数
     */
    private static final Interceptor queryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                    .addQueryParameter("deviceModel", AppUtils.getMobileModel())
                    .build();
            Request request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
    };
    /**
     * 设置头
     */
    private static final Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request = originalRequest.newBuilder()
                    .header("token", "")
                    .method(originalRequest.method(), originalRequest.body())
                    .build();
            return chain.proceed(request);
        }
    };

    /**
     * 设置缓存
     */
    private static final Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetUtil.isNetworkAvailable(MyApplication.getContext())) {
                int maxAge = 0;
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                // String cacheControl = request.cacheControl().toString();
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                return response.newBuilder()
                        // .header("Cache-Control", cacheControl)
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为1周  只对get有用,post没有缓冲
                // long maxStale = 60 * 60 * 24 * 7;
                return response.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("nyn")
                        .build();
            }
        }
    };


//    private Interceptor addQueryParameterInterceptor(){
//        return new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
//                        .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
//                        .addQueryParameter("deviceModel", AppUtils.getMobileModel())
//                        .build();
//                Request request = originalRequest.newBuilder().url(modifiedUrl).build();
//                return chain.proceed(request);
//            }
//        };
//    }

//    private Interceptor addHeaderInterceptor(){
//        return new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//                Request request = originalRequest.newBuilder()
//                        //.header("token", token)
//                        .method(originalRequest.method(), originalRequest.body())
//                        .build();
//                return chain.proceed(request);
//            }
//        };
//    }
/**
 * 云端响应头拦截器，用来配置缓存策略
 * Dangerous interceptor that rewrites the server's cache-control header.
 */
//    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetUtil.isNetworkAvailable(App.getInstance())) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                Logger.e("no network");
//            }
//            Response originalResponse = chain.proceed(request);
//
//            if (NetUtil.isNetworkAvailable(App.getInstance())) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    };


}
