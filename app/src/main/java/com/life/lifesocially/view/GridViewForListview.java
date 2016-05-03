package com.life.lifesocially.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Guojie on 2016/1/31.
 * 解决的问题：GridView显示不全，只显示了一行的图片 重写GridView来解决
 */
public class GridViewForListview extends GridView {
    public GridViewForListview(Context context) {
        super(context);
    }

    public GridViewForListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}