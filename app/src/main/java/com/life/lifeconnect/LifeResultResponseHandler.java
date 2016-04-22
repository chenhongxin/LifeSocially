package com.life.lifeconnect;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface LifeResultResponseHandler {

     void onSuccess(LifeListResponse lifeListResponse);

    void onFail(LifeListResponse lifeListResponse, String error);
}