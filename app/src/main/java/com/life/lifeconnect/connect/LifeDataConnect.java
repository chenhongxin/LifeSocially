package com.life.lifeconnect.connect;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifeconnect.method.LifeDataMethod;
import com.life.lifeconnect.model.Data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/4/22.
 */
public class LifeDataConnect extends LifeConnect {

    @Override
    public <T> Call<LifeListResponse<Data>> getCall(String method, String url, Map<String, String> params, Retrofit retrofit) {
        LifeDataMethod lifeDataMethod = retrofit.create(LifeDataMethod.class);
        Call<LifeListResponse<Data>> call;
        if ("get".equals(method)) {
            call = lifeDataMethod.get(url, params);
        } else {
            call = lifeDataMethod.post(url, params);
        }
        return call;
    }

    @Override
    public void findList(String method, String url, Map<String, String> params, LifeResultResponseHandler lifeResultResponseHandler) {
        Retrofit retrofit = getRetrofit();
        createObservable(getCall(method, url, params, retrofit), lifeResultResponseHandler);
    }
}