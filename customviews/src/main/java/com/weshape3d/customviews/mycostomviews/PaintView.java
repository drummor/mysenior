package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.weshape3d.customviews.R;
import com.weshape3d.customviews.mycostomviews.myimgmanager.BitmapUtil;

/**
 * Created by WESHAPE-DEV02 on 2017/9/28.
 */

public class PaintView extends View{
    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //线性着色器
        Paint paint = new Paint();
//        Shader shader = new LinearGradient(150, 150, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300,300,200,paint);
//
//        //辐射渐变
//        Shader shader2 = new RadialGradient(300, 800, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader2);
//        canvas.drawCircle(300, 800, 200, paint);
//
//        //中心扫描渐变SweepGradient

        //bitmap shader
     Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);

       /* Shader shader3 =  new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader3);
        canvas.drawOval(new RectF(0.f, 0.f, 1000.f,800.f) ,paint);*/
//        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x008800);
//        paint.setColorFilter(lightingColorFilter);
        canvas.drawBitmap( BitmapUtil.handlerImgNegative(bitmap),0,0,paint);
    }
}
