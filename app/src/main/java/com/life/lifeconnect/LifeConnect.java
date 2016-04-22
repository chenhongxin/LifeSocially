package com.life.lifeconnect;

import com.life.lifeconnect.model.Data;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
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

    // 获取回调数据
    public abstract <T> Call<LifeListResponse<Data>> getCall(String method, String url, Map<String, String> params, Retrofit retrofit);
    // 获取数据数组
    public abstract void findList(String method, String url, Map<String, String> params, final LifeResultResponseHandler lifeResultResponseHandler);

    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public <T> void createObservable(final Call<LifeListResponse<T>> finalCall, final LifeResultResponseHandler lifeResultResponseHandler) {
        Observable.create(new Observable.OnSubscribe<LifeListResponse>() {
            @Override
            public void call(Subscriber<? super LifeListResponse> subscriber) {
                try {
                    LifeListResponse lifeListResponse = finalCall.execute().body();
                    subscriber.onNext(lifeListResponse);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<LifeListResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LifeListResponse lifeResponse) {
                String code = lifeResponse.code;
                if ("200".equals(code)) {
                    lifeResultResponseHandler.onSuccess(lifeResponse);
                } else {
                    lifeResultResponseHandler.onFail(lifeResponse, null);
                }
            }
        });
    }

}