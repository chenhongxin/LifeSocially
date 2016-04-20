package com.life.lifesocially.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.life.lifesocially.app.AppManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * 底层
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected Map<String, Object> params = new HashMap<>();
    // 回调跳转界面
    protected IntentCallBack intentCallBack;

    public abstract int getContentViewID();

    public abstract void initData();

    // 初始化控件
    public abstract void initWidget();

    // 初始化控件事件
    public abstract void initWidgetClick();

    // 处理触发事件
    public abstract void widgetClick(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().pushActivity(this);
        setContentView(getContentViewID());
        ButterKnife.bind(this);

        intentCallBack = new IntentCallBack() {
            @Override
            public void startActivityCommon(Class cls) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), cls);
                startActivity(intent);
            }

            @Override
            public void startActivityForResultCommon(Class cls, int result) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), cls);
                startActivityForResult(intent, result);
            }
        };
        initWidget();
        initData();
        initWidgetClick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    public void showToast(String notice) {
        Toast.makeText(this, notice + "", Toast.LENGTH_SHORT).show();
    }

    public void showToastCenter(String text) {
        Toast toast = Toast.makeText(this, text + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public interface IntentCallBack {
        void startActivityCommon(Class cls);

        void startActivityForResultCommon(Class cls, int result);
    }

}