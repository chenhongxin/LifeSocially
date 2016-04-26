package com.life.lifesocially.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.life.lifeconnect.LifeConfig;
import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseActivity;
import com.life.lifesocially.ui.user.LifeLoginActivity;
import com.life.lifesocially.ui.user.LifeRegisterActivity;
import com.zhy.autolayout.config.AutoLayoutConifg;

import butterknife.Bind;

public class WelComeActivity extends BaseActivity {

    @Bind(R.id.b_welcome_register)
    Button b_welcome_register;
    @Bind(R.id.b_welcome_login)
    Button b_welcome_login;
    private final int WELCOME_REGISTER_REQUEST = 0x111;

    @Override
    public int getContentViewID() {
        return R.layout.socially_welcome;
    }

    @Override
    public void initData() {
        LifeConfig.init(this);
        AutoLayoutConifg.getInstance().init(this);
    }

    @Override
    public void initWidget() {
        LifeConnect.getInstance().call("/test/getAllIndustry", params, new LifeResultResponseHandler() {
            @Override
            public void onSuccess(LifeResponse lifeListResponse) {
                Log.i("json", "onSuccess");
            }

            @Override
            public void onFail(String error) {
                showToast(error);
            }
        });
    }

    @Override
    public void initWidgetClick() {
        b_welcome_register.setOnClickListener(this);
        b_welcome_login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.b_welcome_register: {
                intentCallBack.startActivityForResultCommon(LifeRegisterActivity.class, WELCOME_REGISTER_REQUEST);
            }
            break;
            case R.id.b_welcome_login: {
                intentCallBack.startActivityCommon(LifeLoginActivity.class);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case WELCOME_REGISTER_REQUEST:
                if (resultCode == RESULT_OK)
                    finish();
                break;
        }
    }

}