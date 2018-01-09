package com.weshape3d.customviews.mycostomviews.stickyflagview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.weshape3d.customviews.R;

/**
 * Created by WESHAPE-DEV02 on 2017/11/16.
 */

public class MyMapView extends View {
    Camera camera = new Camera();
    Paint paint = new Paint();
    private Bitmap bitmap;

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
        invalidate();
        Log.d("drummor","rotate:"+rotate);
    }

    private float rotate = 0;
    public MyMapView(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        BitmapDrawable drawable  = (BitmapDrawable) context.getDrawable(R.drawable.ic_launcher);
        bitmap = drawable.getBitmap();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
        initView();
    }
    private void initView(){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(MyMapView.this,"rotate",0f,180f);
                animator.start();
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myDraw(canvas);
    }
    private void myDraw(Canvas canvas){

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;

        //画变换的一半
        //先旋转，再裁切，再使用camera执行3D动效,
        // **然后保存camera效果**,最后再旋转回来
        canvas.save();
        canvas.translate(centerX, centerY);//6、回归原点
        canvas.rotate(rotate);//5、旋转回去

        camera.save();
        camera.rotateY(rotate);
        camera.applyToCanvas(canvas);//4、绕Y轴旋转
        camera.restore();

        canvas.clipRect(0, -centerY, centerX, centerY);//3、裁切某个范围
        canvas.rotate(-rotate);             //2、然后逆时针旋转
        canvas.translate(-centerX, -centerY);//1、先把中心点平移到中心

        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

        //画不变换的另一半
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(rotate);
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        //此时的canvas的坐标系已经旋转，所以这里是rotateY
        camera.rotateY(rotate);
        camera.applyToCanvas(canvas);
        canvas.rotate(-rotate);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);

        canvas.restore();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    private void startAni(){

        //1.开始旋转

    }


}
