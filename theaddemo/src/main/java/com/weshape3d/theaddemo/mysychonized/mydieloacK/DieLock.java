package com.weshape3d.theaddemo.mysychonized.mydieloacK;

import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class DieLock extends Thread {
    boolean flag = true;
    MyLock myLock = new MyLock();
    public DieLock (boolean flag){
        this.flag = false;
    }

    @Override
    public void run() {
        super.run();
        if(flag){
            synchronized (myLock.objA){
                Log.d("drummor","使用了A");
                synchronized (myLock.objB){
                    Log.d("drummor","使用了B");

                }
            }
        }else {
            synchronized (myLock.objB){
                Log.d("drummor","使用了A");
                synchronized (myLock.objA){
                    Log.d("drummor","使用了B");
                }
            }
        }
    }
}
