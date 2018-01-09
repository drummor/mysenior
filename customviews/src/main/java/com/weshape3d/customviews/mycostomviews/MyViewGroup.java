package com.weshape3d.customviews.mycostomviews;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Path;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by WESHAPE-DEV02 on 2017/9/18.
 */

public class MyViewGroup extends LinearLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","viewgroup----调用onTouchEvent--"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("drummor","ViewGroup----调用dispatchTouchEvent");
        boolean b =  super.dispatchTouchEvent(ev);
        Log.d("drummor","ViewGroup----结果dispatchTouchEvent---"+b);
        
        return b ;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("drummor","ViewGroup----调用onInterceptTouchEvent"+ev.getAction());
        boolean b =  super.onInterceptTouchEvent(ev);
        Log.d("drummor","ViewGroup----结果onInterceptTouchEvent---"+b);
        Path path = null;
        return b ;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("drummor","draw----调用viewgroup---onLayout");
        super.onLayout(changed, l, t, r, b);
        Log.d("drummor","draw----完成viewgroup---onLayout");
        ArrayMap<String,String> arrayMap = new ArrayMap<>();
        arrayMap.put("","");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d("drummor","draw----调用viewgroup---onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("drummor","draw----完成viewgroup---onMeasure");
    }

    @Override
    protected int getWindowAttachCount() {
        return super.getWindowAttachCount();
    }

    public void getWindoInfo(){

    }
}
