package com.life.lifeconnect;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
public class LifeConnect {

    protected Subscription subscriber;

    public void find(String url, Map<String, String> options, boolean isCookie, final LifeResultResponseHandler lifeResultResponseHandler){
        subscribeOn(getLifeMethod(isCookie).array(url, options), lifeResultResponseHandler);
    }

    public void excute(String url, Map<String, String> options, boolean isCookie, final LifeResultResponseHandler lifeResultResponseHandler){
        subscribeOn(getLifeMethod(isCookie).execute(url, options), lifeResultResponseHandler);
    }

    public void hash(String url, Map<String, String> options, boolean isCookie, final LifeResultResponseHandler lifeResultResponseHandler){
        subscribeOn(getLifeMethod(isCookie).hash(url, options), lifeResultResponseHandler);
    }

    public LifeMethod getLifeMethod(final boolean isCookie) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                                if (isCookie) {
                                    requestBuilder.header("cookie", LifeConfig.getString("cookie"))
                                            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                            .addHeader("Accept-Encoding", "gzip, deflate")
                                            .addHeader("Connection", "keep-alive")
                                            .addHeader("Accept", "*/*")
                                            .addHeader("Cookie", "add cookies here");
                                }
                                Request request = requestBuilder.build();
                                Response response = chain.proceed(request);
                                if(!isCookie) {
                                    String cookie = response.headers().get("Set-Cookie");
                                    String value = cookie.substring(0, cookie.indexOf(";"));
                                    LifeConfig.putString("cookie", value + "");
                                }
                                return response;
                            }
                        })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okClient)
                .build();
        return retrofit.create(LifeMethod.class);
    }

    public <T> T subscribeOn(Observable<LifeResponse<T>> observable, final LifeResultResponseHandler lifeResultResponseHandler) {
        subscriber = observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LifeResponse<T>>() {
            @Override
            public void call(LifeResponse<T> arrayListLifeListResponse) {
                if (!"200".equals(arrayListLifeListResponse.code)) {
                    lifeResultResponseHandler.onFail(arrayListLifeListResponse.msg);
                } else {
                    lifeResultResponseHandler.onSuccess(arrayListLifeListResponse);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                lifeResultResponseHandler.onFail(null, "异常问题");
            }
        });
        return null;
    }

    protected void unSubscription() {
        if (subscriber != null)
            subscriber.unsubscribe();
    }

}