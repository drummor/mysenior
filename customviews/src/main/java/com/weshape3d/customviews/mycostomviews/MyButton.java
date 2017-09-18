package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by WESHAPE-DEV02 on 2017/9/17.
 */

public class MyButton extends TextView{
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
        Log.d("drummor","View --------调用 onTouchEvent-"+event.getAction());
       return super.onTouchEvent(event);
        //return false;
    }
}
