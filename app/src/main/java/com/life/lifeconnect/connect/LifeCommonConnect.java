package com.life.lifeconnect.connect;

import com.life.lifeconnect.CommonBean;
import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResponse;
import com.life.lifeconnect.LifeResultResponseHandler;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 通用的连接器
 */
public class LifeCommonConnect extends LifeConnect {

    @Override
    public void find(String url, Map<String, String> options, boolean isCookie, LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(getLifeMethod(isCookie).array(url, options), lifeResultResponseHandler);
    }

    @Override
    public void excute(String url, Map<String, String> options, boolean isCookie, LifeResultResponseHandler lifeResultResponseHandler) {
        subscribeOn(getLifeMethod(isCookie).execute(url, options), lifeResultResponseHandler);
    }

    @Override
    public void call(String url, Map<String, String> options, boolean isCookie, final LifeResultResponseHandler lifeResultResponseHandler){
        final Call<LifeResponse<String>> call = getLifeMethod(isCookie).call(url, options);
        call.enqueue(new Callback<LifeResponse<String>>() {
            @Override
            public void onResponse(Call<LifeResponse<String>> call, Response<LifeResponse<String>> response) {
                LifeResponse<String> arrayListLifeListResponse = response.body();
                if (!"200".equals(arrayListLifeListResponse.code)) {
                    lifeResultResponseHandler.onFail(arrayListLifeListResponse.msg);
                } else {
                    lifeResultResponseHandler.onSuccess(arrayListLifeListResponse);
                }
            }

            @Override
            public void onFailure(Call<LifeResponse<String>> call, Throwable t) {
                lifeResultResponseHandler.onFail(null, "出现异常");
            }
        });
    }

    @Override
    public void arrayCall(String url, Map<String, String> options, boolean isCookie, final LifeResultResponseHandler lifeResultResponseHandler) {
        final Call<LifeResponse<CommonBean>> call = getLifeMethod(isCookie).arrayCall(url, options);
        call.enqueue(new Callback<LifeResponse<CommonBean>>() {
            @Override
            public void onResponse(Call<LifeResponse<CommonBean>> call, Response<LifeResponse<CommonBean>> response) {
                LifeResponse<CommonBean> arrayListLifeListResponse = response.body();
                if (!"200".equals(arrayListLifeListResponse.code)) {
                    lifeResultResponseHandler.onFail(arrayListLifeListResponse.msg);
                } else {
                    lifeResultResponseHandler.onSuccess(arrayListLifeListResponse);
                }
            }

            @Override
            public void onFailure(Call<LifeResponse<CommonBean>> call, Throwable t) {
                lifeResultResponseHandler.onFail(null, "出现异常");
            }
        });
    }

}