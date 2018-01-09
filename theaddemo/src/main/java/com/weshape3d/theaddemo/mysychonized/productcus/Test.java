package com.weshape3d.theaddemo.mysychonized.productcus;

/**
 * Created by WESHAPE-DEV02 on 2017/10/30.
 * 多线程多消费
 */


/**
 * 先notify再wait,因为如果你先wait了就表示 当前的线程就不是currentThread了
 */
public class Test {
    public  static  void productCus(){
        Thread thread = new Thread(new Cus("消费A"));
        Thread thread1 = new Thread(new Cus("消费B"));
        Thread thread2 = new Thread(new Product("生产A"));
        Thread thread3 = new Thread(new Product("生产B"));
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
