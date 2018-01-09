package com.weshape3d.customviews.mycostomviews.stickyflagview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.weshape3d.customviews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WESHAPE-DEV02 on 2017/10/11.
 */


/**
 * 问题1,再次Add到原来布局的时候，可能会失效
 * 问题2：快速滑动的时候：会卡主，放在中央找不到了
 * 问题3：post（）的作用是
 *     public boolean post(Runnable action) {
        final AttachInfo attachInfo = mAttachInfo;
             if (attachInfo != null) {
            return attachInfo.mHandler.post(action);
            }

 // Postpone the runnable until we know on which thread it needs to run.
 // Assume that the runnable will be successfully placed after attach.
         getRunQueue().post(action);
             return true;
         }

 问题4:动画处理不完， 不接收手势
 问题5：atan sin 计算
 问题6：贝塞尔曲线的使用
 为题7： 在Window改变位置，而不是在window中绘制控件.。
 */

public class StickyFlagView extends View {

    private Context context;

    private ViewGroup parent;
    private ViewGroup.LayoutParams originalLp; // view原始layout
    private int[] originalLocation; // view原始的location
    private int originalWidth; // view原始的宽度
    private int originalHeight; // view原始的高度

    private float stickRadius; // 黏贴半径

    private int flagColor; // 标记颜色
    private int flagTextColor; // 标记文本颜色
    private float maxDragDistance; // 最大拖拽距离
    private String flagText; // 标记文本
    private float flagTextSize; // 标记文本大小
    private float flagRadius; // 标记半径
    private Bitmap flagBitmap; // 标记图片
    private float maxStickRadius; // 最大黏贴半径
    private float minStickRadius; // 最小黏贴半径
    private float rate = 0.8f;

    private boolean isFirstSizeChange = true;
    private boolean isTouched;
    private boolean isReachLimit; // 是否达到最大拖拽距离
    private boolean isRollBackAnimating; // 回滚动画是否在执行
    private boolean isDisappearAnimating; // 消失动画是否在执行
    private boolean isFlagDisappear; // 标记是否消失
    private boolean isViewLoadFinish; // view是否加载完毕
    private boolean isViewInWindow; // view是否在window中

    private int which;
    private List<Integer> disappearRes;

    private PointF stickPoint; // 黏贴点
    private PointF dragFlagPoint; // 拖拽标记点
    private Paint flagPaint; // 标记画笔
    private Paint flagTextPaint; // 标记文本画笔
    private Path flagPath;
    private int visibility;



    public StickyFlagView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public StickyFlagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public StickyFlagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private WindowManager windowManager = null;
    private void init(){
        Log.d("drummor","---- init---");
        maxDragDistance = ScreenUtils.getScreenHeight(getContext())/6;
        flagPath = new Path();
        originalLocation = new int[2];
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        stickRadius = 30;
        flagRadius = 30;
        flagPaint = new Paint();
        flagPaint.setColor(Color.RED);
        flagPaint.setStyle(Paint.Style.FILL);
        stickPoint = new PointF();
        dragFlagPoint = new PointF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("drummor","---- onsize---");
        if(isFirstSizeChange){
            isFirstSizeChange = false;
            parent = (ViewGroup) getParent();
            // 记录view原始layout参数
            originalLp = getLayoutParams();
            originalWidth = w;
            originalHeight = h;

            //获取在屏幕中的位置
            getLocationOnScreen(originalLocation);
            //记录在屏幕中的原始位置
            originalLocation[1] = originalLocation[1] - ScreenUtils.getStatusHeight(context);

            // 黏贴点在原始view的中心点
            stickPoint.set((float) (originalWidth * 0.5), (float) (originalHeight * 0.5));
        }else {
            // view的size改变之后，修正黏贴点坐标
            if (originalWidth == w && originalHeight == h) {
                stickPoint.set((float) (originalWidth * 0.5), (float) (originalHeight * 0.5));
            } else {
                stickPoint.x += originalLocation[0];
                stickPoint.y += originalLocation[1];
            }

        }

    }

    /**
     * 当按下的时候就把这个View添加到Window中去；
     * 把View加到Window中；
     */
    private void addViewInWindow(){
        if (parent != null) {
            // 将view从它的父控件中移除
            parent.removeView(this);
        }
        //创建View在Window中的布局
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        //window的类型有好多种
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 不设置这个弹出框的透明遮罩显示为黑色
        layoutParams.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.START | Gravity.TOP;

        layoutParams.width =  ScreenUtils.getScreenWidth(getContext())/2;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.x = 0;
        layoutParams.y = 0;
        if(isViewInWindow ==false){
            windowManager.addView(this,layoutParams);
            post(new Runnable() {
                @Override
                public void run() {
                    isViewInWindow = true;
                    Log.d("drummor","已经添加到Window中了");
                }
            });
        }
    }

    private void restoreView(){
        if(isViewInWindow){
            windowManager.removeView(StickyFlagView.this);
            stickRadius = 20;
            isViewInWindow = false;
            Log.d("drummor","撤出window");
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(StickyFlagView.this.getParent()==null&&!isViewInWindow){
                    parent.addView(StickyFlagView.this,originalLp);
                }
            }
        });
        Log.d("drummor"," 加到布局中");
    }

    private void drawDisappearFlagBitmap(Canvas canvas) {
        if (disappearRes != null) {
            Drawable drawable = context.getResources().getDrawable(disappearRes.get(which));
            if (drawable != null) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                canvas.drawBitmap(bitmap, (float) (dragFlagPoint.x - bitmap.getWidth() * 0.5),
                        (float) (dragFlagPoint.y - bitmap.getHeight() * 0.5), flagPaint);
            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.getHeight();
         //canvas.drawColor(Color.GRAY);
        if(isTouched&&isViewInWindow) {//在Touch在已经添加到View中去了
            //绘制拖拽点
            if(isReachLimit){
                //绘制贝塞尔曲线
                drawStickCurve(canvas);
                //绘制原点
                drawStickCircle(canvas);
                canvas.drawCircle(dragFlagPoint.x, dragFlagPoint.y, flagRadius, flagPaint);
                Log.d("drummor", "在Window中draw 贝塞尔");
            }else {
                Log.d("drummor","在Window中draw 原点");
                canvas.drawCircle(dragFlagPoint.x, dragFlagPoint.y, flagRadius, flagPaint);

            }

        } else{
            if(isDisappearAnimating){
                drawDisappearFlagBitmap(canvas);
            }
            Log.d("drummor","在原布局draw");
            if(!isViewInWindow){
                stickRadius = 30;
                drawStickCircle(canvas);
            }

        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("drummor","--onAttachedToWindow---");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor",event.getAction()+"---Action");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                isTouched  = true;
                Log.d("drummor","----按下----");
                addViewInWindow();
                break;
            case MotionEvent.ACTION_MOVE :
                isTouched = true;
                isReachLimit = true;
                float dx =  event.getRawX() - stickPoint.x;
                float dy =  event.getRawY() - stickPoint.y;
                float distanc = (float) Math.sqrt((Math.pow(dx,2))+Math.pow(dy,2));
                float xie =  distanc/maxDragDistance;
                if(xie<=1){
                    isReachLimit = true;
                    stickRadius = 30*(1-xie);
                    drag(event);
                }else {
                    isReachLimit = false;
                    drag(event);
                }
                break;
            case MotionEvent.ACTION_UP :
                Log.d("drummor","----抬起----");
                isReachLimit = false;
                isTouched = false;
                float dx2 =  event.getRawX() - stickPoint.x;
                float dy2 =  event.getRawY() - stickPoint.y;
                float distanc2 = (float) Math.sqrt((Math.pow(dx2,2))+Math.pow(dy2,2));
                float xie2 =  distanc2/maxDragDistance;
                if(xie2<=1){
                    restoreView();
                }else {
                    if(isViewInWindow){
                      launchDisappearAnimation(500);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL :
                Log.d("drummor","----ACTION_CANCEL-----");
                break;
        }
        return true;
    }

    private void drawStickCurve(Canvas canvas) {

        //求出弧度值
        double angle = Math.atan((dragFlagPoint.y - stickPoint.y) / (dragFlagPoint.x - stickPoint.x));
        //离原点距离
        float stickOffsetX = (float) (stickRadius * Math.sin(angle));
        float stickOffsetY = (float) (stickRadius * Math.cos(angle));
        float flagOffsetX = (float) (flagRadius * Math.sin(angle));
        float flagOffsetY = (float) (flagRadius * Math.cos(angle));

        float x1 = stickPoint.x - stickOffsetX;
        float y1 = stickPoint.y + stickOffsetY;

        float x2 = dragFlagPoint.x - flagOffsetX;
        float y2 = dragFlagPoint.y + flagOffsetY;

        float x3 = dragFlagPoint.x + flagOffsetX;
        float y3 = dragFlagPoint.y - flagOffsetY;

        float x4 = stickPoint.x + stickOffsetX;
        float y4 = stickPoint.y - stickOffsetY;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        canvas.drawPoint(x1,y1,paint);
        canvas.drawPoint(x2,y2,paint);
        canvas.drawPoint(x3,y3,paint);
        canvas.drawPoint(x4,y4,paint);
        // 曲线控制点
        float controlPointX = (float) ((stickPoint.x + dragFlagPoint.x) * 0.5);
        float controlPointY = (float) ((stickPoint.y + dragFlagPoint.y) * 0.5);

        flagPath.reset();
        flagPath.moveTo(x1, y1);
        flagPath.quadTo(controlPointX, controlPointY, x2, y2);
        flagPath.lineTo(x3, y3);
        flagPath.quadTo(controlPointX, controlPointY, x4, y4);
        flagPath.lineTo(x1, y1);

        canvas.drawPath(flagPath, flagPaint);
    }

    /**
    /**
     * 恢复
     */
    Handler handler = new Handler();
    private void goback(){
                //0在Runnable中实现run方法，View的measure、layout等事件后触发run
                if(isViewInWindow){
                    windowManager.removeView(StickyFlagView.this);
                    stickRadius = 20;
                    isViewInWindow = false;
                    Log.d("drummor","撤出window");
                }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(StickyFlagView.this.getParent()==null&&!isViewInWindow){
                                parent.addView(StickyFlagView.this,originalLp);
                            }
                        }
                    });
                    Log.d("drummor"," 加到布局中");
              //invalidate()
    }

    /**
     * 拖拽
     */

    private void drag(MotionEvent event){
        dragFlagPoint.x = event.getRawX();
        dragFlagPoint.y = event.getRawY() - ScreenUtils.getStatusHeight(context);
        postInvalidate();
    }
    /**
     * 画圆
     * @param canvas
     */
    private void drawStickCircle(Canvas canvas) {
        Log.d("drummor","圆心—x"+stickPoint.x+":"+stickPoint.y+":"+stickRadius);
        canvas.drawCircle(stickPoint.x, stickPoint.y, stickRadius, flagPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("drummor","---onLayout --- ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("drummor","---onMeasure --- ");
    }


    private void launchDisappearAnimation(long duration) {
        disappearRes = new ArrayList<>();
        disappearRes.add(R.drawable.disappear0);
        disappearRes.add(R.drawable.disappear1);
        disappearRes.add(R.drawable.disappear2);
        disappearRes.add(R.drawable.disappear3);
        disappearRes.add(R.drawable.disappear4);

        isDisappearAnimating = true;
        ValueAnimator animator = ValueAnimator.ofInt(0, 4);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                which = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isFlagDisappear = true;
                isDisappearAnimating = false;
                restoreView();
            }
        });
        animator.start();
    }

    /**
     * 当home键按下的时候不让他在屏幕上显示了
     * @param visibility
     */
//    @Override
//    protected void onWindowVisibilityChanged(int visibility) {
//        super.onWindowVisibilityChanged(visibility);
//        if(visibility == View.GONE)
//            if(windowManager!=null&&isAttachedToWindow()&&isViewInWindow){
//                post(new Runnable() {
//                    @Override
//                    public void run() {
//                        goback();
//                    }
//                });
//
//            }
//
//    }
}
