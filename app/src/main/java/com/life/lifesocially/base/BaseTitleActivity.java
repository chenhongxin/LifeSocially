package com.life.lifesocially.base;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.life.lifesocially.R;

/**
 * 标题
 */
public abstract class BaseTitleActivity extends BaseActivity {

    protected TextView titleText;
    protected LinearLayout backBtn;
    protected TextView rightText;
    protected ImageButton rightBtn;
    
    @SuppressLint("WrongViewCast")
    @Override
    public void initWidget() {
      findViewById(R.id.activity_back_btn).setOnClickListener(this);
      titleText = (TextView) findViewById(R.id.activity_title_text);
      backBtn = (LinearLayout) findViewById(R.id.activity_back_btn);
      backBtn.setOnClickListener(this);
      rightText = (TextView) findViewById(R.id.activity_right_text);
      rightBtn = (ImageButton) findViewById(R.id.activity_right_btn);
    }

    public void setTitle(String title) {
        titleText.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_back_btn: {
                finish();
            }
            break;
        }
    }

    public void setRightText(String text) {
        rightText.setText(text + "");
        rightText.setVisibility(View.VISIBLE);
    }

}