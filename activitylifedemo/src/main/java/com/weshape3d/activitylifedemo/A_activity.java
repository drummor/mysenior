package com.weshape3d.activitylifedemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class A_activity extends Activity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_activity);
        findViewById(R.id.tv).setOnClickListener(this);
        findViewById(R.id.bt_dialog).setOnClickListener(this);
        Log.d("drummor","A  ----onCreate");
        ViewGroup  viewGroup = new LinearLayout(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("drummor","A  ----onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("drummor","A  ----onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("drummor","A  ----onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("drummor","A  ----onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("drummor","A  ----onDestroy");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv){
            startActivity(new Intent(this,B_activity.class));
        }else {
            Dialog dialog = new Dialog(this);
            dialog.setTitle("只是一个Dialog");
            dialog.show();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("drummor","A  ----onRestart");
    }
}
