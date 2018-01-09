package com.weshape3d.customviews.mycostomviews.mywindowmanagerdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Entity;
import android.graphics.PixelFormat;
import android.os.DropBoxManager;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by WESHAPE-DEV02 on 2017/9/19.
 */

public class MyWindowManger {
    Context context = null;
    public  MyWindowManger(Context context){
        this.context = context;
        WindowManager windowManager = (WindowManager) (context).getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.format = PixelFormat.TRANSPARENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.START | Gravity.TOP;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.x = 0;
        layoutParams.y = 0;

    }
}
