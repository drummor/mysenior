package com.weshape3d.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class ActivityB extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("B");
        findViewById(R.id.bt).setOnClickListener(this);
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ActivityC.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("drummor","onNewIntent - ActivityB");
    }
}
