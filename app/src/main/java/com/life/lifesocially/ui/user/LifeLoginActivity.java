package com.life.lifesocially.ui.user;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseTitleActivity;
import com.life.lifesocially.utis.Base64;
import com.life.lifesocially.utis.CheckUtils;

import butterknife.Bind;

/**
 * 登录
 */
public class LifeLoginActivity extends BaseTitleActivity {

    @Bind(R.id.telephone_text)
    EditText telephone_text;
    @Bind(R.id.password_text)
    EditText password_text;
    @Bind(R.id.login_btn)
    Button login_btn;
    @Bind(R.id.iv_clear)
    ImageView iv_clear;
    @Bind(R.id.see_pwd)
    ToggleButton see_pwd;

    @Override
    public int getContentViewID() {
        return R.layout.activity_life_login;
    }

    @Override
    public void initData() {
        setTitle("登录");
    }

    @Override
    public void initWidgetClick() {
        login_btn.setOnClickListener(this);
        iv_clear.setOnClickListener(this);
        telephone_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!TextUtils.isEmpty(telephone_text.getText().toString())) {
                        iv_clear.setVisibility(View.VISIBLE);
                    } else {
                        iv_clear.setVisibility(View.GONE);
                    }
                } else {
                    iv_clear.setVisibility(View.GONE);
                }
            }
        });
        see_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn: {
                String phone = telephone_text.getText().toString();
                String password = password_text.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                if (!TextUtils.isEmpty(CheckUtils.isNotPassword(password))) {
                    showToast(CheckUtils.isNotPassword(password));
                    return;
                }
                params.clear();
                params.put("userPhone", Base64.encode(phone));
                params.put("userPwd", Base64.encode(password));
                progressDialog.startProgressDialog();
                new LifeConnect().hash("/app/appLogin.json", params, false, new LifeResultResponseHandler() {

                    @Override
                    public void onSuccess(LifeResponse lifeListResponse) {

                    }

                    @Override
                    public void onFail(LifeResponse lifeListResponse, String error) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });
            }
            break;
            case R.id.iv_clear:
                telephone_text.setText("");
                break;
        }
    }

}