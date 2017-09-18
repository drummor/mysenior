package com.weshape3d.myapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("drummor","你收呢三街坊建瓯市是打飞机司法鉴定三街坊");
        Log.d("drummor","dasfndsjofsd"+new MyJNI().add(2,8));

    }
}
