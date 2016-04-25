package com.life.lifesocially.ui.user;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseTitleActivity;
import com.life.lifesocially.utis.Base64;
import com.life.lifesocially.utis.CheckUtils;

import butterknife.Bind;

public class LifeRegisterActivity extends BaseTitleActivity {

    @Bind(R.id.iv_phone_clear)
    ImageView iv_phone_clear;
    @Bind(R.id.iv_password_clear)
    ImageView iv_password_clear;
    @Bind(R.id.telephone_text)
    EditText telephone_text;
    @Bind(R.id.password_text)
    EditText password_text;
    @Bind(R.id.code_btn)
    Button code_btn;
    @Bind(R.id.code_text)
    EditText code_text;
    @Bind(R.id.register_btn)
    Button register_btn;
    MyCount count = new MyCount(60000, 1000);

    @Override
    public int getContentViewID() {
        return R.layout.activity_life_register;
    }

    @Override
    public void initData() {
        setTitle("注册");
    }

    @Override
    public void initWidgetClick() {
        code_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        iv_phone_clear.setOnClickListener(this);
        iv_password_clear.setOnClickListener(this);
        telephone_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!TextUtils.isEmpty(telephone_text.getText().toString())) {
                        iv_phone_clear.setVisibility(View.VISIBLE);
                    } else {
                        iv_phone_clear.setVisibility(View.GONE);
                    }
                } else {
                    iv_phone_clear.setVisibility(View.GONE);
                }
            }
        });
        password_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!TextUtils.isEmpty(telephone_text.getText().toString())) {
                        iv_password_clear.setVisibility(View.VISIBLE);
                    } else {
                        iv_password_clear.setVisibility(View.GONE);
                    }
                } else {
                    iv_password_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.code_btn: {
                String phone = telephone_text.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                count.start();
                code_btn.setEnabled(false);
                params.clear();
                params.put("code", "101");
                params.put("phone", Base64.encode(phone));
                progressDialog.startProgressDialog();
                new LifeConnect().excute("/app/sendMessage.json", params, false, new LifeResultResponseHandler() {
                    @Override
                    public void onSuccess(final LifeResponse lifeListResponse) {
                        code_text.setText(lifeListResponse.data + "");
                        progressDialog.stopProgressDialog();
                        showToast(lifeListResponse.msg);
                    }

                    @Override
                    public void onFail(LifeResponse lifeListResponse, String error) {
                        showToast(error);
                        progressDialog.stopProgressDialog();
                        code_btn.setText("获取验证码");
                        code_btn.getBackground().setAlpha(255);
                        code_btn.setEnabled(true);
                    }

                    @Override
                    public void onFail(String error) {
                        showToast(error);
                        progressDialog.stopProgressDialog();
                        code_btn.setText("获取验证码");
                        code_btn.getBackground().setAlpha(255);
                        code_btn.setEnabled(true);
                    }
                });
            }
            break;
            case R.id.register_btn: {
                String phone = telephone_text.getText().toString();
                String password = password_text.getText().toString();
                String code = code_text.getText().toString();
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
                if (TextUtils.isEmpty(code)) {
                    showToast("请输入验证码");
                    return;
                }
                if(code.length() != 6){
                    showToast("验证码需要6位长度");
                }
                params.clear();
                params.put("userPhone", Base64.encode(phone));
                params.put("userPwd", Base64.encode(password));
                params.put("verifCode", code);
                params.put("source", 1 + "");
                progressDialog.startProgressDialog();
                new LifeConnect().excute("/app/appRegister.json", params, true, new LifeResultResponseHandler() {
                    @Override
                    public void onSuccess(LifeResponse lifeListResponse) {
                        showToast(lifeListResponse.msg);
                        progressDialog.stopProgressDialog();
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFail(LifeResponse lifeListResponse, String error) {
                        showToast(error);
                        progressDialog.stopProgressDialog();
                    }

                    @Override
                    public void onFail(String error) {
                        showToast(error);
                        progressDialog.stopProgressDialog();
                    }
                });
            }
            break;
            case R.id.iv_phone_clear:
                telephone_text.setText("");
                break;
            case R.id.iv_password_clear:
                password_text.setText("");
                break;
        }
    }

    private void enable() {
        boolean result = false;
        String phone = telephone_text.getText().toString();
        boolean isMobile = CheckUtils.isMobileNO(phone);
        if (isMobile) {
            if (password_text.getText().length() > 6) {
                if (code_text.getText().length() == 6) {
                    result = true;
                }
            }
        }
        if (telephone_text.hasFocus()) {
            if (!TextUtils.isEmpty(phone)) {
                iv_phone_clear.setVisibility(View.VISIBLE);
            } else {
                iv_phone_clear.setVisibility(View.GONE);
            }
        }
        if (password_text.hasFocus()) {
            if (!TextUtils.isEmpty(phone)) {
                iv_password_clear.setVisibility(View.VISIBLE);
            } else {
                iv_password_clear.setVisibility(View.GONE);
            }
        }
        register_btn.setEnabled(result);
    }

    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            code_btn.setText((millisUntilFinished / 1000) + "秒");
        }

        @Override
        public void onFinish() {
            code_btn.setText("获取验证码");
            code_btn.getBackground().setAlpha(255);
            code_btn.setEnabled(true);
        }
    }

}