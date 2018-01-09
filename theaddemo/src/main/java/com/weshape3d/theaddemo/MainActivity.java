package com.weshape3d.theaddemo;

import android.content.AbstractThreadedSyncAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weshape3d.theaddemo.mysychonized.mydieloacK.DieLock;
import com.weshape3d.theaddemo.mysychonized.productcus.Cus;
import com.weshape3d.theaddemo.mysychonized.productcus.Product;
import com.weshape3d.theaddemo.mysychonized.productcus.Test;
import com.weshape3d.theaddemo.singledemo.Singleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Test.productCus();
    }

}
