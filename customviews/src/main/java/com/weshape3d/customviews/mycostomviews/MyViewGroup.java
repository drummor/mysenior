package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        return b ;
    }
}
