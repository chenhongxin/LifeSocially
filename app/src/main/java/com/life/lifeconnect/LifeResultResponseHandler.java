package com.life.lifeconnect;

/**
 * LifeResultResponseHandler
 */
public interface LifeResultResponseHandler {

     void onSuccess(LifeResponse lifeListResponse);

    void onFail(LifeResponse lifeListResponse, String error);

    void onFail(String error);

}