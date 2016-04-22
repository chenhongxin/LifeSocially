package com.life.lifeconnect;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 *
 */
public abstract class LifeConnect {

//    // 获取回调数据
//    public abstract <T> Observable<LifeListResponse<T>> getCall(String method, String url, Map<String, String> params, Retrofit retrofit);
//    // 获取数据数组
//    public abstract <T> T findList(String method, String url, Map<String, String> params, final LifeResultResponseHandler lifeResultResponseHandler);

    public Retrofit getRxRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

}