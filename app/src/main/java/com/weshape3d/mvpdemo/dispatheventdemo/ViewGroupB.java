package com.weshape3d.mvpdemo.dispatheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by WESHAPE-DEV02 on 2017/9/5.
 */

public class ViewGroupB extends LinearLayout {
    public ViewGroupB(Context context) {
        super(context);
        initView();
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    public void initView(){
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("drummor","B dispatchTouchEvent");
        return  super.dispatchTouchEvent(ev);


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       Log.d("drummor","B onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","B onTouchEvent" + MyFlag.actions[event.getAction()]);
        return super.onTouchEvent(event);

    }

}
