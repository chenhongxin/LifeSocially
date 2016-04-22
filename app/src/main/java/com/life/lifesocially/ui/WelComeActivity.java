package com.life.lifesocially.ui;

import android.util.Log;
import android.view.View;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeListResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifeconnect.connect.LifeDataConnect;
import com.life.lifeconnect.model.Data;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseActivity;

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
        new LifeDataConnect().findList("get", "/test/listTestEntity.json", params, new LifeResultResponseHandler() {
            @Override
            public void onSuccess(LifeListResponse lifeListResponse) {
                for (Object object : lifeListResponse.data){
                    Data data = (Data) object;
                    Log.i("json", data.id + ":" + data.title);
                }
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