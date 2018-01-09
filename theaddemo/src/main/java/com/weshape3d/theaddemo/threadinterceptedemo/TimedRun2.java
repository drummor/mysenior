package com.weshape3d.theaddemo.threadinterceptedemo;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by WESHAPE-DEV02 on 2017/10/31.
 */



    public class TimedRun2 {
        private static final ExecutorService taskExec = Executors.newCachedThreadPool();

        public static void timedRun(Runnable r,
                                    long timeout, TimeUnit unit)
                throws InterruptedException {
            Future<?> task = taskExec.submit(r);
            try {
                task.get(timeout, unit);
            } catch (TimeoutException e) {
                // 因超时而取消任务
            } catch (ExecutionException e) {
                // 任务异常，重新抛出异常信息
                throw new TimedRun.LaunderThrowable().launderThrowable(e.getCause());
            } finally {
                // 如果该任务已经完成，将没有影响
                // 如果任务正在运行，将因为中断而被取消
                task.cancel(true); // interrupt if running
            }
        }
    }



