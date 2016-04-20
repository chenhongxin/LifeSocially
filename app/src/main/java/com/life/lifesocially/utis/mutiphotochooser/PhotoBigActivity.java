package com.life.lifesocially.utis.mutiphotochooser;

import java.util.ArrayList;

import android.view.View;

import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseActivity;

/**
 * Created by wangtao on 16/2/1.
 */
public class PhotoBigActivity extends BaseActivity {

    private ArrayList<String> imageItemArrayList;

    @Override
    public void initWidget() {

        imageItemArrayList = getIntent().getStringArrayListExtra("list");
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_big;
    }

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initWidgetClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
