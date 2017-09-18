package com.weshape3d.mvpdemo.dispatheventdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by WESHAPE-DEV02 on 2017/9/5.
 */

public class ViewC extends View {
    public ViewC(Context context) {
        super(context);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","C  onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("drummor","C  dispatchTouchEvent"+System.currentTimeMillis());
        return  super.dispatchTouchEvent(event);
    }
}
