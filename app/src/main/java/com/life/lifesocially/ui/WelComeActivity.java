package com.life.lifesocially.ui;

import android.util.Log;
import android.view.View;

import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifeconnect.connect.LifeCommonConnect;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseActivity;
import com.life.lifesocially.utis.Base64;

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
        params.put("code", "101");
        params.put("phone", Base64.encode("15221089157"));
        new LifeCommonConnect().excute("/app/sendMessage.json", params, new LifeResultResponseHandler() {
            @Override
            public void onSuccess(LifeListResponse lifeListResponse) {
                Log.i("json", "onSuccess");
            }

            @Override
            public void onFail(LifeListResponse lifeListResponse, String error) {
                Log.i("json", "onFail");
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