package com.life.lifeconnect;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
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
public class LifeConnect<K> {

    // 单例
    private static LifeConnect lifeConnect;

    public static LifeConnect getInstance() {
        if (lifeConnect == null) {
            synchronized (LifeConnect.class) {
                if (lifeConnect == null) {
                    lifeConnect = new LifeConnect();
                }
            }
        }
        return lifeConnect;
    }

    protected Subscription subscriber;
    /**
     * 所有头
     */
    private HashMap<String, String> headerMap = new HashMap<String, String>();
    /**
     * 表示是否要传cookie过去验证
     */
    private boolean isCookie;

    public void excute(String url, Map<String, String> options, final LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(getLifeMethod().execute(url, options), lifeResultResponseHandler);
    }

    public void hash(String url, Map<String, String> options, final LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(getLifeMethod().hash(url, options), lifeResultResponseHandler);
    }

    public void call(String url, Map<String, String> options, final LifeResultResponseHandler lifeResultResponseHandler){
        call(getLifeMethod().call(url, options), lifeResultResponseHandler);
    }

    public LifeMethod getLifeMethod() {
        OkHttpClient okClient = getOkHttpClient();
        Retrofit retrofit = getRetrofit(okClient);
        return retrofit.create(LifeMethod.class);
    }

    // 获取OkHttpClient对象
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                                if (isCookie) {
                                    requestBuilder.header("cookie", LifeConfig.getString("cookie"));
                                }
                                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                                }
                                Request request = requestBuilder.build();
                                Response response = chain.proceed(request);
                                // 存储cookie
                                if (!isCookie) {
                                    String cookie = response.headers().get("Set-Cookie");
                                    if(!TextUtils.isEmpty(cookie)) {
                                        String value = cookie.substring(0, cookie.indexOf(";"));
                                        LifeConfig.putString("cookie", value + "");
                                    }
                                }
                                return response;
                            }
                        })
                .build();
    }

    // 获取Retrofit对象
    private Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    // 使用rxandroid描述
    public <T> T subscribeOn(Observable<LifeResponse<T>> observable, final LifeResultResponseHandler lifeResultResponseHandler) {
        subscriber = observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LifeResponse<T>>() {
            @Override
            public void call(LifeResponse<T> listLifeResponse) {
                if (!"200".equals(listLifeResponse.code)) {
                    lifeResultResponseHandler.onFail(listLifeResponse.msg);
                } else {
                    lifeResultResponseHandler.onSuccess(listLifeResponse);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                lifeResultResponseHandler.onFail("异常问题");
            }
        });
        return null;
    }

    // 使用rxandroid调用
    public void call(Call call, final LifeResultResponseHandler lifeResultResponseHandler){
        call.enqueue(new Callback<LifeResponse<ArrayList<HashMap<String, Object>>>>() {
            @Override
            public void onResponse(Call<LifeResponse<ArrayList<HashMap<String, Object>>>> call, retrofit2.Response<LifeResponse<ArrayList<HashMap<String, Object>>>> response) {
                LifeResponse<ArrayList<HashMap<String, Object>>> listLifeResponse = response.body();
                if (!"200".equals(listLifeResponse.code)) {
                    lifeResultResponseHandler.onFail(listLifeResponse.msg);
                } else {
                    lifeResultResponseHandler.onSuccess(listLifeResponse);
                }
            }

            @Override
            public void onFailure(Call<LifeResponse<ArrayList<HashMap<String, Object>>>> call, Throwable t) {
                lifeResultResponseHandler.onFail("异常问题");
            }
        });
    }

    protected void unSubscription() {
        if (subscriber != null)
            subscriber.unsubscribe();
    }

    // 构建者
    public LifeConnect isCookie(boolean isCookie){
        lifeConnect.isCookie = isCookie;
        return lifeConnect;
    }

    /**
     * 添加头
     *
     * @param key
     * @param value
     * @return
     */
    public LifeConnect addHeader(String key, String value) {
        lifeConnect.headerMap.put(key, value);
        return lifeConnect;
    }

    public LifeConnect clearHeader() {
        lifeConnect.headerMap.clear();
        return lifeConnect;
    }

}