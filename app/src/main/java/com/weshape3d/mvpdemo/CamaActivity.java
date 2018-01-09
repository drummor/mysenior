package com.weshape3d.mvpdemo;

import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.weshape3d.mvpdemo.R;

public class CamaActivity extends AppCompatActivity {
    View fan,zheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cama);

        fan = findViewById(R.id.fan);
        zheng = findViewById(R.id.zheng);
        final ObjectAnimator animator = ObjectAnimator.ofFloat(zheng,"rotationY",0,180).setDuration(500);
        Log.d("drummor","distance"+zheng.getCameraDistance());
        int distance = 160000;
        float scale = getResources().getDisplayMetrics().widthPixels*2;
        zheng.setCameraDistance(zheng.getCameraDistance()*3000);
        zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }
}
