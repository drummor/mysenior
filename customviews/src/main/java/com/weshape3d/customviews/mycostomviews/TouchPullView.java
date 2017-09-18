package com.weshape3d.customviews.mycostomviews;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by WESHAPE-DEV02 on 2017/9/13.
 */

public class TouchPullView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCircleRadius = 100;
    private float mProgresss;
    private DecelerateInterpolator deceInter = new DecelerateInterpolator();
    public TouchPullView(Context context) {
        this(context,null);
    }
    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        paint.setAntiAlias(true);
        paint.setDither(true);//防抖动
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
    }
    private int measureWidth,measureHeight;

    private int myDragHeight = 1000;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int iWidth = 4*mCircleRadius+getPaddingRight()+getPaddingLeft();
        int iHeight = (int) ((myDragHeight*deceInter.getInterpolation(mProgresss)+0.5f)+getPaddingTop()+getPaddingBottom());
        Log.d("drummor","设置的iHeight"+iHeight);
        if(widthMode == MeasureSpec.EXACTLY){
            measureWidth = width;
        }else {
            measureWidth = iWidth;
        }
        if(heightMode == MeasureSpec.EXACTLY){
            measureHeight = (int) (height*deceInter.getInterpolation(mProgresss)+0.5f);
        }else {
            measureHeight = (int) Math.max(iHeight,height*deceInter.getInterpolation(mProgresss)+0.5f);
        }
        setMeasuredDimension(measureWidth,measureHeight);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = getWidth()>>1;
        float y = getHeight()>>1;//右移表示除以2
        canvas.drawCircle(x,y,mCircleRadius,paint);
    }
    public void setmProgresss(float mProgresss){
        this.mProgresss = mProgresss;
        requestLayout();
    }

    public float getProgresss() {
        return mProgresss;
    }


    public void release(){
        ValueAnimator animator = ValueAnimator.ofFloat(getProgresss(),0f).setDuration(400);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(animation.getAnimatedValue()instanceof Float){
                    setmProgresss((Float) animation.getAnimatedValue());
                }
            }
        });
        animator.start();
    }
}
