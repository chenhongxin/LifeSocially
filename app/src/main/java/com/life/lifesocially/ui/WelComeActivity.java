package com.life.lifesocially.ui;

import android.util.Log;
import android.view.View;

import com.life.lifeconnect.LifeConfig;
import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.connect.LifeDataConnect;
import com.life.lifeconnect.method.LifeDataMethod;
import com.life.lifeconnect.model.Data;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseActivity;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class WelComeActivity extends BaseActivity {

    @Override
    public int getContentViewID() {
        return R.layout.socially_welcome;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWidget() {
//        new LifeDataConnect().findList("get", "/test/listTestEntity.json", params, new LifeResultResponseHandler() {
//            @Override
//            public void onSuccess(LifeListResponse<Data> lifeListResponse) {
//                for (Object object : lifeListResponse.data){
//                    Data data = (Data) object;
//                    Log.i("json", data.id + ":" + data.title);
//                }
//            }
//
//            @Override
//            public void onFail(LifeListResponse lifeListResponse, String error) {
//                Log.i("json", "onFail");
//            }
//        });
//        new LifeDataConnect<Data>().findList("/test/listTestEntity.json", params);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LifeConfig.getApiHost())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        LifeDataMethod<Data> lifeDataMethod = retrofit.create(LifeDataMethod.class);
        lifeDataMethod.get("/test/listTestEntity.json", params).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<LifeListResponse<Data>>() {
            @Override
            public void call(LifeListResponse<Data> dataLifeListResponse) {
                Log.i("json", "call");
            }
        });
    }

    @Override
    public void initWidgetClick() {

    }

    @Override
    public void widgetClick(View v) {

    }

}