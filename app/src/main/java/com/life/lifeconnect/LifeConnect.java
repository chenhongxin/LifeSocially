package com.life.lifeconnect;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * LifeConnect
 */
public abstract class LifeConnect {

    protected Subscription subscriber;

    public abstract void find(String url, Map<String, String> options, final LifeResultResponseHandler lifeResultResponseHandler);

    public abstract void excute(String url, Map<String, String> options, final LifeResultResponseHandler lifeResultResponseHandler);

    protected LifeMethod lifeMethod = getLifeMethod();

    public LifeMethod getLifeMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(LifeMethod.class);
    }

    public <T> T subscribeOn(Observable<LifeListResponse<T>> observable, final LifeResultResponseHandler lifeResultResponseHandler) {
        subscriber = observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LifeListResponse<T>>() {
            @Override
            public void call(LifeListResponse<T> arrayListLifeListResponse) {
                lifeResultResponseHandler.onSuccess(arrayListLifeListResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                lifeResultResponseHandler.onFail(null, "您的网络出现了问题，请检查您的网络");
            }
        });
        return null;
    }

    protected void unSubscription() {
        if (subscriber != null)
            subscriber.unsubscribe();
    }

}