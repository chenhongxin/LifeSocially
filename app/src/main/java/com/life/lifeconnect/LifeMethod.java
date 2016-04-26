package com.life.lifeconnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * LifeMethod
 */
public interface LifeMethod {
    // 执行返回一个字符串
    @GET("{url}")
    Observable<LifeResponse<String>> execute(@Path("url") String url, @QueryMap Map<String, String> options);
    // 执行返回一个HashMap这个对象比较复杂
    @GET("{url}")
    Observable<LifeResponse<HashMap<String, Object>>> hash(@Path("url") String url, @QueryMap Map<String, String> options);
    @GET("{url}")
    Call<LifeResponse<ArrayList<HashMap<String, Object>>>> call(@Path("url") String url, @QueryMap Map<String, String> options);
}