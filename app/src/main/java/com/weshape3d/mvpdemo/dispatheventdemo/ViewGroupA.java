package com.weshape3d.mvpdemo.dispatheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by WESHAPE-DEV02 on 2017/9/5.
 */

public class ViewGroupA extends LinearLayout {
    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("drummor","A dispatchTouchEvent --"+MyFlag.actions[ev.getAction()]+"---"+System.currentTimeMillis());
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("drummor","A onInterceptTouchEvent "+MyFlag.actions[ev.getAction()]);
       return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","A onTouchEvent "+MyFlag.actions[event.getAction()]);
        super.onTouchEvent(event);
        return false;
    }
}
