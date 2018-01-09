package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by WESHAPE-DEV02 on 2017/9/17.
 */

public class MyButton extends android.support.v7.widget.AppCompatTextView{
    public MyButton(Context context) {
        super(context);
    }
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("drummor","View --------调用 dispatchTouchEvent-"+event.getAction());
        boolean b = super.dispatchTouchEvent(event);
        Log.d("drummor","View --------结果dispatchTouchEvent ----"+event.getAction()+"---"+b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","View --------调用 onTouchEvent-"+event.getAction()+"getMetaState"+event.getMetaState());
       return super.onTouchEvent(event);
        //return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("drummor","draw----调用view---onMeasure--"+MeasureSpec.getSize(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("drummor","draw----完成view---onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("drummor","draw----调用onLayout---调用onLayout");
        super.onLayout(changed, left, top, right, bottom);
        Log.d("drummor","draw----调用view---调用onLayout");
    }
}
