package com.weshape3d.customviews;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.weshape3d.customviews.activity.RulerActivity;
import com.weshape3d.customviews.mycostomviews.MapView;
import com.weshape3d.customviews.mycostomviews.RulerWidget;
import com.weshape3d.customviews.mycostomviews.ScreenUtils;
import com.weshape3d.customviews.mycostomviews.TouchPullView;
import com.weshape3d.customviews.mycostomviews.stickyflagview.StickyFlagView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private float moveStartX;
    private float moveStartY;
    private float MAXY = 600f;
    private TouchPullView touchPullView;
    private Button button;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("drummor","Activity中Window"+getWindow().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,MapViewAcitivity.class));
        findViewById(R.id.bt).setOnClickListener(this);
       // startActivity(new Intent(this,CusActivity.class));
       //startActivity(new Intent(this,NestedScrollActivity.class));
        //startActivity(new Intent(this, StickViewActivity.class));
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
       // startActivity(new Intent(this,MyBasicviewActivity.class));

        // startActivity(new Intent(this, ScrollActivity.class));
        //startActivity(new Intent(this, MySwipeRefreshLayoutActivity.class));
        //startActivity(new Intent(this, DialogDemoActivity.class));
        // startActivity(new Intent(this, ThreadDemoActivity.class));

       // touchPullView = (TouchPullView) findViewById(R.id.pull_view);
//        findViewById(R.id.mybutton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("drummor","-----OnClickListener") ;
//            }
//        }

        findViewById(R.id.mybutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpop(v);
            }
        });
        findViewById(R.id.show_dl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    void showpop(View v){
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(100);
        popupWindow.setHeight(200);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_demo,null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(v);

        Log.d("drummor","popwindowID"+view.getWindowId()+"");
    }

    private void setMyTouchPullView(){
        findViewById(R.id.main_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                int y = 0;
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        moveStartY = event.getY();
                        return  true;
                    case MotionEvent.ACTION_MOVE:
                        float nowY = event.getY();
                        float moveLength = nowY - moveStartY;
                        if(moveLength>0){
                            float mProgress = moveLength>=MAXY?1:moveLength/MAXY;
                            touchPullView.setmProgresss(mProgress);
                            return  true;
                        }
                        break;
                    case  MotionEvent.ACTION_UP:
                        touchPullView.release();
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("drummor","getMetaState"+ev.getMetaState());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    boolean inWindow = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    void showDialog(){
        Dialog dialog = new Dialog(this);
        TextView textView =  new TextView(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_demo,null);
        textView.setText("dnafjos");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(19);
        dialog.setContentView(view);
        dialog.show();
        Log.d("drummor","dialog中Window"+dialog.getWindow().toString());

    }

    TextView textView;
    ViewGroup viewGroup;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bt :
                if(viewGroup == null){
                     viewGroup = (ViewGroup) findViewById(android.R.id.content);
                }

                FrameLayout.LayoutParams params =
//                      FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                        new FrameLayout.LayoutParams(
                                ScreenUtils.getScreenHeight(getApplicationContext())/3,
                                ScreenUtils.getScreenHeight(getApplicationContext())/3
                        );

                if(textView == null){
                    textView = new TextView(this);
                    textView.setTextColor(Color.BLUE);
                    textView.setText("你这是福建省");
                    textView.setTextColor(Color.RED);
                    textView.setTextSize(30);
                    textView.setBackgroundColor(Color.BLACK);
                }

                if(textView.getParent()==null){
                    viewGroup.addView(textView,params);
                    inWindow =true;
                    Log.d("drummor1","添加成功");
                }
                break;
        }
    }


}
