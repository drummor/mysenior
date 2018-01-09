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
        int count = event.getPointerCount();
        //获取触控所属手指的位置
        int indext = event.getActionIndex();
        //获取手指ID
        int pointID = event.getPointerId(indext);
        //根据ID获取在指针
        int index =  event.findPointerIndex(indext);
        switch (event.getActionMasked()){
            case  MotionEvent.ACTION_DOWN:
                Log.d("drummor1","单点DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("drummor1","单点UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("drummor1","MOVE");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("drummor1","其他手指DOWN");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("drummor1","其他手指UP");
                break;

        }
        Log.d("drummor1","多点: ---手指数量:"+count+"---Action:"+event.getAction()+"---"+"位置:"+indext+"ID:"+pointID+"---index:"+index);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("drummor","C  dispatchTouchEvent"+System.currentTimeMillis());
        return  super.dispatchTouchEvent(event);
    }
}
