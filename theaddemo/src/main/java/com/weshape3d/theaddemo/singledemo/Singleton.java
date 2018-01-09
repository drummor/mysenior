package com.weshape3d.theaddemo.singledemo;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class Singleton {
    public Singleton(){};
    private static class  SingletonHolder{
        private static  Singleton SINGLETON = new Singleton();
    }
    public static Singleton getSingleton(){
        return SingletonHolder.SINGLETON;
    }
}