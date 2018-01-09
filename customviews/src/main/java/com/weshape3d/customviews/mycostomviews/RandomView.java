package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by WESHAPE-DEV02 on 2017/10/16.
 */

public class RandomView extends View{
    Paint paint = null;
    public RandomView(Context context) {
        super(context);
        init();
    }

    public RandomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RandomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    int[] nums = new int[100];
    private void init(){
        paint = new Paint();
        paint.setStrokeWidth(15);
        paint.setColor(Color.RED);
        for(int i =0;i<=100;i++){
            nums[0] = (int) (Math.random()*10);
        }
        Arrays.sort(nums);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int perHeight = height/10;
        int perWidth = width/100;
        for(int i = 0 ;i<100;i++){
            canvas.drawPoint(i*perWidth,i*perHeight,paint);
        }
    }
}
