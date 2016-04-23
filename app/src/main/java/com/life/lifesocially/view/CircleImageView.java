package com.life.lifesocially.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.life.lifesocially.R;

/**
 * Created by wangtao on 16/2/2.
 */
public class CircleImageView extends ImageView {

    public CircleImageView(Context context) {
        super(context);
        init(null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        isCircle = a.getBoolean(R.styleable.CircleImageView_circle, false);
        rect_adius = a.getDimension(R.styleable.CircleImageView_radius, rect_adius);
        init(attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private final RectF roundRect = new RectF();
    private float rect_adius = 5;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();
    private boolean isCircle;

    public void setIsCircle(boolean isCircle) {
        this.isCircle = isCircle;
    }

    private void init(AttributeSet attrs) {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        float density = getResources().getDisplayMetrics().density;
        rect_adius = rect_adius * density;
    }

    public void setRectAdius(float adius) {
        rect_adius = adius;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        if (isCircle) {
            rect_adius = getWidth()/2;
        }
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
