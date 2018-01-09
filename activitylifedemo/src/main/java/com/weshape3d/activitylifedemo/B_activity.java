package com.weshape3d.activitylifedemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class B_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_activity);
        Log.d("drummor","B  ----onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("drummor","B  ----onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("drummor","B  ----onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("drummor","B  ----onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("drummor","B  ----onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("drummor","B  ----onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("drummor","B  ----onRestart");
    }
}
