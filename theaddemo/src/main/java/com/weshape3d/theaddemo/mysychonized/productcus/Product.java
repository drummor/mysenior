package com.weshape3d.theaddemo.mysychonized.productcus;

import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class Product implements Runnable {
    private String flag = "";
    public static boolean  isProduct = false;
    public static int myNumber = 0;
    public Product(String flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        work();
    }

    public void work(){
        synchronized (Product.class){
                while (isProduct){//已经生产了
                    gotoWait();
                }
                product();
                Product.class.notifyAll();
            }
        Log.d("drummor",flag +"完成了");
    }
    private void  product(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myNumber +=1;
        isProduct = true;
        Log.d("drummor",flag+"---"+Product.myNumber);
    }

    private void gotoWait(){
        try {
            Product.class.notifyAll();
            Product.class.wait();
            Log.d("drummor",flag+"wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
