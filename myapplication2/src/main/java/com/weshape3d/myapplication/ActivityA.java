package com.weshape3d.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity  implements View.OnClickListener{
    TextView tv_mes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        setTitle("A");
        findViewById(R.id.bt).setOnClickListener(this);
        tv_mes = (TextView) findViewById(R.id.tv_mes);
        tv_mes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int  id = v.getId();
        if(id == R.id.bt){
            Intent intent = new Intent(this,ActivityB.class);

            startActivity(intent);
        }else if(id == R.id.tv_mes){

                tv_mes.setText(PhoneUtil.getPhoneMessage(this));

        }

    }

    private void MyIntent(){
        HandlerThread handlerThread = new HandlerThread("1",1);
        handlerThread.start();
    }
}
