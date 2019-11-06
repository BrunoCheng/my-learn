package com.suixin.concurrent.L2线程并发工具类.L6Future;

import com.suixin.commons.utils.SleepTools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description 演示Future和Callable的关系
 * @Created by acheng
 */
public class UseFuture {

    /*实现Callable接口，允许有返回值*/
    private static class UseCallable implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算");
            Thread.sleep(2000);
            for (int i = 0; i < 5000; i++) {
                sum = sum + i;
            }
            System.out.println("Callable子线程计算完成，结果=" + sum);
            return sum;
        }

    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        //new一个Callable实例
        UseCallable useCallable = new UseCallable();
        //futuretask 进行接收，相当于将callable包装成了Runnable
        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
        //将callable交给Thread执行
        new Thread(futureTask).start();
        Random r = new Random();
        //主线程休眠一秒钟
        SleepTools.second(1);
        //随机决定是获得结果还是终止任务
        if (r.nextBoolean()) {

            System.out.println("Get UseCallable result = " + futureTask.get());
        } else {
            System.out.println("中断计算");
            futureTask.cancel(true);
        }
    }
}