package com.weshape3d.myapplication;

/**
 * Created by WESHAPE-DEV02 on 2017/9/12.
 */

public class MyJNI {
    static {
        System.loadLibrary("hello");
    }
    public native int add(int x,int y);
    public native int[] increaseArray(int[] array);
}
