package com.life.lifeconnect.connect;

import android.util.Log;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifeconnect.method.LifeDataMethod;
import com.life.lifeconnect.model.Data;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/4/22.
 */
public class LifeDataConnect<T> extends LifeConnect {

    public void findList(String url, Map<String, String> options){
        Retrofit retrofit = getRxRetrofit();
        LifeDataMethod<Data> lifeDataMethod = retrofit.create(LifeDataMethod.class);
        lifeDataMethod.get(url, options).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LifeListResponse<Data>>() {
            @Override
            public void call(LifeListResponse<Data> dataLifeListResponse) {
                Log.i("json", "call");
            }
        });
    }

}