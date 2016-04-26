package com.life.lifeconnect;

/**
 * LifeResultResponseHandler
 */
public interface LifeResultResponseHandler {

    void onSuccess(LifeResponse lifeListResponse);

    void onFail(String error);

}