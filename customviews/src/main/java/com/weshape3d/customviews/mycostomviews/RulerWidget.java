package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import java.text.DecimalFormat;

/**
 * Created by WESHAPE-DEV02 on 2017/10/24.
 */

public class RulerWidget extends View {

    private Paint paintScale = null;
    private Paint paintTextScale = null;
    private Paint paintText = null;
    private float scaleDx = ScreenUtils.dpToPx(10);

    public RulerWidget(Context context) {
        super(context);
        initPaint();
    }

    public RulerWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RulerWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private Scroller mScroller;
    private int mMinimumVelocity,mMaximumVelocity;
    private void initPaint(){

        mScroller = new Scroller(getContext());
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

        paintScale = new Paint();
        paintScale.setAntiAlias(true);
        paintScale.setColor(Color.BLACK);
        paintScale.setStrokeWidth(3);
        paintScale.setStyle(Paint.Style.FILL);

        paintTextScale = new Paint();
        paintTextScale.setColor(Color.BLACK);
        paintTextScale.setTextSize(12*3);

        paintText = new Paint();
        paintText.setColor(Color.GREEN);
        paintText.setTextSize(18*3);
        paintText.setStrokeWidth(ScreenUtils.dpToPx(2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.GRAY);
        drawScale(canvas);
        drawText(canvas);
    }
    private float topDx = ScreenUtils.dpToPx(50);
    //画Text
    private void drawText(Canvas canvas){
        float f = getScrollX()/scaleDx+ ScreenUtils.getScreenWidth()/2/scaleDx;
        DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String text=decimalFormat.format(f);

        float centerX = getScrollX()+ ScreenUtils.getScreenWidth()/2;
        canvas.drawText(text+"",centerX - paintText.measureText(text+"")/2, ScreenUtils.dpToPx(30),paintText);
        canvas.drawLine(centerX,ScreenUtils.dpToPx(40), centerX, ScreenUtils.dpToPx(60),paintText);
        if(rulerCallBack!=null){
            rulerCallBack.changeScale(text);
        }
    }
    //draw刻度尺子
    private void drawScale(Canvas canvas){
        canvas.drawLine(0,topDx,
                ScreenUtils.dpToPx(10)*99,topDx,
                paintScale);
        canvas.save();
        canvas.translate(0,topDx);
        for(int i =0 ;i<100;i++){
            if(i%10 == 0){
                canvas.drawLine(i* scaleDx,0,scaleDx*i,ScreenUtils.dpToPx(10),paintScale);
                float x = scaleDx*i - paintTextScale.measureText(i+"")/2;
                float y = ScreenUtils.dpToPx(40);
                canvas.drawText(i+"",x,y,paintTextScale);
            }else {
                canvas.drawLine(i* scaleDx,0,scaleDx*i,ScreenUtils.dpToPx(5),paintScale);
            }
        }
        canvas.translate(0,-topDx);
        canvas.restore();
    }

    private float lastX = 0;
    private float motionDx = 0;
    private VelocityTracker mVelocity;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(!mScroller.isFinished()){
            mScroller.abortAnimation();
        }
        if(action == MotionEvent.ACTION_DOWN){
            if(getScrollX()<0){
                scrollTo(0,0);
            }
            lastX = event.getX();
        }else if(action == MotionEvent.ACTION_MOVE){
            if(mVelocity == null){
                mVelocity = VelocityTracker.obtain();
            }
            mVelocity.addMovement(event);
            float nowX = event.getX();
            motionDx = lastX - nowX;
            lastX = nowX;
            if(motionDx+getScrollX()<=0){
                scrollBy((int)(motionDx*0.5),0);
                return true;
            }
            if(motionDx+getScrollX()>=scaleDx*99-ScreenUtils.getScreenWidth()){
                scrollTo((int) (scaleDx*99-ScreenUtils.getScreenWidth()),0);
                return true;
            }
            scrollBy((int)motionDx,0);
        }else if(action == MotionEvent.ACTION_UP){
            if(getScrollX()<0){
                scrollTo(0,0);
            }
                mVelocity.computeCurrentVelocity(500);
                int mVeloX = -(int) mVelocity.getXVelocity();
                fling(mVeloX);
        }
        return  true;
    }

    public void fling(int velocityX) {
            mScroller.fling(getScrollX(), getScrollY(), velocityX, 0, -100, mMaximumVelocity, 0, mMaximumVelocity);
    }

    @Override
    public void scrollBy(@Px int x, @Px int y) {
        Log.d("drummor","scrollBy");
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        Log.d("drummor","scrollTo");
        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        Log.d("drummor","computeScroll");
        //普通的滑动
        if(mScroller.computeScrollOffset()){
            if(mScroller.getCurrX()<0){
                scrollTo(0,0);
            }else{
                scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            }

        }

    }

    public interface RulerCallBack{
        public void changeScale(String textScale);
    }
    private RulerCallBack rulerCallBack;
    public void setRulerCallBack(RulerCallBack rulerCallBack){
        this.rulerCallBack = rulerCallBack;
    }
}
