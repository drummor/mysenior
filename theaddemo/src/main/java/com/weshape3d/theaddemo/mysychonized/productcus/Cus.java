package com.weshape3d.theaddemo.mysychonized.productcus;

import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 */

public class Cus implements Runnable {
    private String flag = "";
    public Cus(String flag){
        this.flag = flag;
    }
    @Override
    public void run() {
       synchronized (Product.class){

               while (!Product.isProduct){//没有生产
                   gotoWait();
               }
               cus();
               Product.class.notifyAll();
           Log.d("drummor",flag +"完成了");
           }


    }

    private void cus(){
        Log.d("drummor",flag+"---"+Product.myNumber);
        Product.isProduct = false;
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
