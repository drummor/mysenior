package com.weshape3d.customviews;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by WESHAPE-DEV02 on 2017/10/24.
 */

public class MyApp extends Application {
    private static MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app =this;
    }
    public static MyApp getApp(){
        return app;
    }
}
