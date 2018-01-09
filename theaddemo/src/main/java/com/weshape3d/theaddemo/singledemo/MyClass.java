package com.weshape3d.theaddemo.singledemo;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class MyClass extends Singleton{
    private MyClass() {
        super();
    }

    public static Singleton getSingleton(){
       return null;
    }
}
