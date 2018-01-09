package com.weshape3d.customviews;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class MyBasicviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_basicview);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "null";
            }
        };
        String value =  threadLocal.get();
        threadLocal.set("abc");
        String str = getResources().getString(getResources().getIdentifier("app_name", "string", "h3c.plugina"));
        List< ? super Number> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list.addAll(list1);
        myFun(list);
        // list.add
        list.add(new Integer(1));
        list.add(new Float(2.1f));
        Float f  = (Float) list.get(2);
        list.add(new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        });
    }
    class Student{

    }
    class college extends Student{
    }

    public void myFun(List<? super Integer> list){


    }
}
