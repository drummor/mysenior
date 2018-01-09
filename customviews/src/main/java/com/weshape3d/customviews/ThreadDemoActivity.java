package com.weshape3d.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ThreadDemoActivity extends AppCompatActivity {
boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);

    }
    public void click(View v){
    int id = v.getId();
        if(id == R.id.start){
            thread.start();
            Log.d("drummor","线程状态"+thread.isAlive());
        }else {
            Log.d("drummor","线程状态"+thread.isAlive());
            flag= false;

        }
    }
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {

            while (flag){
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("drummor","线程状态跑完了");
            }

        }
    });
}
