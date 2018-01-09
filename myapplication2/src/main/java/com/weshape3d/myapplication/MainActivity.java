package com.weshape3d.myapplication;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private WebView wv;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv = (android.webkit.WebView) findViewById(R.id.wv);
        textView = (TextView) findViewById(R.id.tv1);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.setWebViewClient(new WebViewClient(){
        });
        //wv.loadUrl("http://cdn.weshape3d.com/w3d002/1002/web/index.html");
         wv.loadUrl("http://www.baidu.com");
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            makeRightEvent();
                Log.d("drummor","执行了右滑2");
            }
        });
        wv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("drummor","Action:"+event.getAction()+"---X:"+event.getX()+"----Y:"+event.getY());
                return false;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"带你受打击案件发生的",Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void makeRightEvent2() {

        long downTime = SystemClock.uptimeMillis();
        MotionEvent motionEventDowm = MotionEvent.obtain(downTime, downTime + 10, MotionEvent.ACTION_DOWN, textView.getRight()+10, textView.getTop()+10, 0);
        textView.dispatchTouchEvent(motionEventDowm);
        MotionEvent motionEventMove = MotionEvent.obtain(downTime, downTime + 20, MotionEvent.ACTION_MOVE, textView.getRight()+10, textView.getTop()+10, 0);
        textView.dispatchTouchEvent(motionEventDowm);
        MotionEvent motionEventup = MotionEvent.obtain(downTime, downTime + 20, MotionEvent.ACTION_UP, textView.getRight()+10, textView.getTop()+10, 0);
        textView.dispatchTouchEvent(motionEventup);
    }

    private void makeRightEvent(){
        long downTime = SystemClock.uptimeMillis();
        MotionEvent motionEventDowm = MotionEvent.obtain(downTime,downTime+10,MotionEvent.ACTION_DOWN,wv.getLeft()+10,wv.getTop()+10,0);
        wv.dispatchTouchEvent(motionEventDowm);

        for(int i = 0;i<15;i++){
            MotionEvent motionEventMove= MotionEvent.obtain(downTime,downTime+i*10,MotionEvent.ACTION_MOVE,wv.getLeft()+10,wv.getTop()+i*10,0);
            wv.dispatchTouchEvent(motionEventMove);
        }

        MotionEvent motionEventUp= MotionEvent.obtain(downTime,downTime+110,MotionEvent.ACTION_UP,wv.getLeft()+10,wv.getTop()+90,0);
        wv.dispatchTouchEvent(motionEventUp);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
      Map map =  Collections.synchronizedMap(new HashMap<>() );
        return super.dispatchTouchEvent(ev);
    }


}
