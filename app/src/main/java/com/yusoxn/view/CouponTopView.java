package com.yusoxn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 背景下方为锯齿状的FrameLayout
 * <p>
 * Created by Yusxon on 17/3/24.
 */

public class CouponTopView extends FrameLayout {

    /**
     * 半圆画笔
     */
    private Paint halfCirclePaint;
    /**
     * 半圆个数
     */
    private int halfCircleNum;
    /**
     * 半圆半径
     */
    private float halfCircleRadius;

    /**
     * view高度
     */
    private int viewHeight;

    public CouponTopView(Context context) {
        this(context, null);
    }

    public CouponTopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponTopView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setWillNotDraw(false);
        init();
    }

    private void init() {

        halfCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        halfCirclePaint.setDither(true);
        halfCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        halfCirclePaint.setStyle(Paint.Style.FILL);

        halfCircleRadius = 0;
        halfCircleNum = 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHeight = h;

        halfCircleNum = 15;
        halfCircleRadius = w / (halfCircleNum * 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = halfCircleRadius * 2;
        canvas.drawCircle(x, viewHeight, halfCircleRadius, halfCirclePaint);
        for (int i = 1; i < halfCircleNum; ++i) {
            x += halfCircleRadius * 3;
            canvas.drawCircle(x, viewHeight, halfCircleRadius, halfCirclePaint);
        }
    }
}
