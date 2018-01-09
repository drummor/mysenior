package com.weshape3d.theaddemo.threadinterceptedemo;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * Created by WESHAPE-DEV02 on 2017/10/31.
 */

public class TimedRun{
    private static final ScheduledExecutorService cancelExec = newScheduledThreadPool(1);
    /**
     *
     * @param r  要运行的任务
     * @param timeout 超时时间
     * @param unit    时间单位
     * @throws InterruptedException  中断异常
     */
    public void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
                    //开启任务子线程  启动线程
                    RethrowableTask rethrowableTask = new RethrowableTask(r);//包装过的Runable 具有记录线程抛出异常的能力
                    final Thread taskThread = new Thread(rethrowableTask);//创建一个线程去执行
                    taskThread.start();
                    InterceptRun interceptRun = new InterceptRun(taskThread);
                    //定时中断任务子线程
                    cancelExec.schedule(interceptRun, timeout, unit);
                    //限时等待任务线程执行完毕 在timeout之后再执行当前线程
                    taskThread.join(unit.toMillis(timeout));
                    //尝试抛出task在执行中抛出到异常
                    rethrowableTask.rethrow();
             }
    /**
     * 中断任务
     */
    class InterceptRun implements Runnable{
        Thread thread;
        InterceptRun( Thread thead){
            this.thread = thead;
        }
        @Override
        public void run() {
            thread.interrupt();
        }
    }

    class RethrowableTask implements Runnable {
        private volatile Throwable t;
        private Runnable r;
        RethrowableTask (Runnable r){
            this.r = r;
        }
        public void run() {
            try {
                r.run();
            } catch (Throwable t) {
                //中断策略，保存当前抛出的异常，退出
                this.t = t;
            }
        }
        // 再次抛出异常
        void rethrow() {
            if (t != null)
                throw (new LaunderThrowable()).launderThrowable(t);
        }
    }

    public static  class LaunderThrowable {


        public  RuntimeException launderThrowable(Throwable t) {
            if (t instanceof RuntimeException)
                return (RuntimeException) t;
            else if (t instanceof Error)
                throw (Error) t;
            else
                throw new IllegalStateException("Not unchecked", t);
        }
    }

}
