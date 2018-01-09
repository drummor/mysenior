package com.weshape3d.theaddemo.threadinterceptedemo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by WESHAPE-DEV02 on 2017/10/31.
 * 加标志位的方法检查 标志位取消
 */

public class PrimeGenerator implements Runnable {
    //定义一个线程池
    private static ExecutorService exec = Executors.newCachedThreadPool();
    //定义一个List
    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    //取消标志位
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        //每次在生成下一个素数时坚持是否取消
        //如果取消，则退出
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }
    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
