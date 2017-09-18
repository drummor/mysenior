package com.weshape3d.customviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.weshape3d.customviews.dawabledemo.DrawableDemoActivity;
import com.weshape3d.customviews.mycostomviews.TouchPullView;

public class MainActivity extends AppCompatActivity {
    private float moveStartX;
    private float moveStartY;
    private float MAXY = 600f;
    private TouchPullView touchPullView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, ScrollActivity.class));
       // touchPullView = (TouchPullView) findViewById(R.id.pull_view);
//        findViewById(R.id.mybutton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("drummor","-----OnClickListener") ;
//            }
//        }
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
}
