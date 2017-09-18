package com.weshape3d.mvpdemo;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer[] integers = new Integer[]{1,2,3,4,5,6,7,8};
       // final Observable mObservable=Observable.fromArray(integers);
        final Observable mObservable = Observable.just(10,12,13,14,15,16);
        final Observer mObserver=new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Integer value) {
                Log.d("drummor","数字："+value);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }

        };

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CamaActivity.class));
                mObservable.subscribe(mObserver);
                findViewById(R.id.bt).setTranslationX(100);
                findViewById(R.id.bt).animate().translationX(500).setDuration(10000).setInterpolator(new AccelerateDecelerateInterpolator(){
                    @Override
                    public float getInterpolation(float input) {
                        float f = super.getInterpolation(input);
                        return f;
                    }
                });

            }
        });

        String[] strings = new String[] {"A","B","C","D","E","F","G"};
        Observable.fromArray(strings).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
        findViewById(R.id.view_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("drummor","ViewB onclick");
            }
        });
        findViewById(R.id.view_b).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("drummor","ViewB onTouch");
                return false;
            }
        });
        findViewById(R.id.v_viewc).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("drummor","viewC OnTouch");
                return false;
            }
        });

    }



}
