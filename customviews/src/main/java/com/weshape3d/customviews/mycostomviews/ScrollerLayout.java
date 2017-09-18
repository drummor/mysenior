package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by WESHAPE-DEV02 on 2017/9/17.
 */

public class ScrollerLayout extends ViewGroup {
    private final int mTouchSlop;
    private Scroller mScroller;
    private int leftBorder;
    private int rightBorder;
    private int downX;
    private int lastMoveX;
    private int moveX;


    public ScrollerLayout(Context context) {
        this(context,null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 为测量每个子控件的大小
         */
        int childCount = getChildCount();
        for(int i =0;i<childCount;i++){
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(!changed){
           return;
        }
        int childCount = getChildCount();
        for(int i = 0;i<childCount;i++){
            View child =  getChildAt(i);
            child.layout(i*child.getMeasuredWidth(),0,(i+1)*child.getMeasuredWidth(),child.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("drummor","onInterceptTouchEvent"+ev.getAction());
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                lastMoveX = downX;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = (int) ev.getX();
                float diff = Math.abs(moveX - downX);
                lastMoveX = moveX;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("drummor","onTouchEvent"+event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                lastMoveX = downX;
                return  true;
            case MotionEvent.ACTION_MOVE:
                /**
                 * etX()是表示Widget相对于自身左上角的x坐标,而getRawX()是表示相对于屏幕左上角的x坐标值
                 */
                moveX = (int) event.getRawX();
                int scrolledX = (lastMoveX - moveX);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                lastMoveX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
