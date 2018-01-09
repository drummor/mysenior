package com.weshape3d.theaddemo.threadinterceptedemo;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by WESHAPE-DEV02 on 2017/10/31.
 */

public class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;
    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        super.run();
        try {
            BigInteger p = BigInteger.ONE;
            //使用中断的方式来取消任务
            while (!Thread.currentThread().isInterrupted())
                //put方法会隐式检查并响应中断
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* 允许任务退出 */
        }
    }

    public void cancel() {
        interrupt();

    }

}
