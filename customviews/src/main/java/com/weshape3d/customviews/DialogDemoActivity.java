package com.weshape3d.customviews;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.weshape3d.customviews.mycostomviews.MyDialog;

public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        findViewById(R.id.my_tv).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDialog();
           }
       });
        findViewById(R.id.my_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

    }

    private void showPopupWindow(){
        PopupWindow popupWindow = new PopupWindow(findViewById(R.id.my_tv),300,400,true);
       View view =  LayoutInflater.from(this).inflate(R.layout.activity_dialog_demo,null);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(findViewById(R.id.my_tv));
    }
    private void showDialog(){
       MyDialog myDialog = new MyDialog(this);
      myDialog.setTitle("你好");
        myDialog.show();
    }

    @Override
    protected void onStart() {
        Log.d("drummor","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("drummor","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("drummor","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("drummor","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("drummor","onDestroy");
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }
}
