package com.weshape3d.theaddemo.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class LokckDemo {
    static  Lock lock = new ReentrantLock();
    public static  void runSom(){
        if (lock.tryLock()) {//如果lock直接进入
            try {
            } finally {
                lock.unlock();
            }
        }else {
            //没有获得锁
        }

    }

    public static  void RunSome2(){
        try{
            lock.lock();//如果没有获取到就等待
        }finally {
            lock.unlock();
        }
    }

    public static void runSome3(){
        try {
            if(lock.tryLock(1000, TimeUnit.SECONDS)){
                try {

                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  static  void runSome4(){
        try {
            lock.lockInterruptibly();//中断操作
            //操作

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

