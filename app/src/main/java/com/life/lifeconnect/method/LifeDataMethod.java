package com.life.lifeconnect.method;

import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.model.Data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 连接获取数据
 */
public interface LifeDataMethod<T> {

    @GET("{url}")
    Observable<LifeListResponse<T>> get(@Path("url") String url, @QueryMap Map<String, String> options);

    @GET("{url}")
    Call<LifeListResponse<Data>> post(@Path("url") String url, @QueryMap Map<String, String> options);

}