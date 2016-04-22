package com.life.lifeconnect.connect;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResultResponseHandler;

import java.util.Map;

/**
 * Created by Administrator on 2016/4/23.
 */
public class LifeCommonConnect extends LifeConnect {

    @Override
    public void find(String url, Map<String, String> options, LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(lifeMethod.array(url, options), lifeResultResponseHandler);
    }

    @Override
    public void excute(String url, Map<String, String> options, LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(lifeMethod.execute(url, options), lifeResultResponseHandler);
    }
}