package com.life.lifesocially.utis.mutiphotochooser;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;

import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseTitleActivity;
import com.life.lifesocially.utis.mutiphotochooser.adapter.PhotoGridAdapter;

/**
 * Created by wangtao on 16/1/19.
 */
public class PhotoSelectedActivity extends BaseTitleActivity {

    private GridView gridView;

    private PhotoGridAdapter gridAdapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_selected;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

	@Override
	public void initData() {
		
	}

	@Override
	public void initWidget() {
		super.initWidget();
        setTitle("已选照片");
        gridView = (GridView) findViewById(R.id.photo_select_gridview);
        gridAdapter = new PhotoGridAdapter(this);
        gridView.setAdapter(gridAdapter);
	}
	
	@Override
	public void initWidgetClick() {
        findViewById(R.id.photo_add_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(PhotoSelectedActivity.this, PhotoSelectedActivity.class), 1);
            }
        });
	}

	@Override
	public void widgetClick(View v) {
		
	}

}