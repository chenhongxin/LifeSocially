package com.life.lifeconnect;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * LifeMethod
 */
public interface LifeMethod {
    @GET("{url}")
    Observable<LifeListResponse<String>> execute(@Path("url") String url, @QueryMap Map<String, String> options);
    @GET("{url}")
    Observable<LifeListResponse<ArrayList<CommonBean>>> array(@Path("url") String url, @QueryMap Map<String, String> options);
}