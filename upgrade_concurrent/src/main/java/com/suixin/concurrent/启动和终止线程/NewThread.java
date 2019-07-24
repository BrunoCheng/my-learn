package com.suixin.concurrent.启动和终止线程;

import java.util.concurrent.*;

/**
 * @Description:开启线程的三种方法
 * @data:2016/7/13
 * @Auther:acheng
 */
public class NewThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NewThreadByThread newThreadByThread = new NewThreadByThread();
        newThreadByThread.run();
        System.out.println(Thread.currentThread().getName());//main

        NewThreadByRunnable newThreadByRunnable = new NewThreadByRunnable();
        //创建Runnable实现类的实例，并以此实例作为Thread的target对象，即该Thread对象才是真正的线程对象
        new Thread(newThreadByRunnable).start();


        NewThreadByCallable newThreadByCallable = new NewThreadByCallable();
        //Callable与Runnable不同,Thread方法不知此Callable,为了将Callable放给Thread,将Callable包装成Runnable
        FutureTask<String> stringFutureTask = new FutureTask<>(newThreadByCallable);
        new Thread(stringFutureTask).start();

        //而且可以获取Callable返回的对象值
        System.out.println(stringFutureTask.get());


        //多线程情况下获取Callable返回的对象值
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<String> future1 = es.submit(newThreadByCallable);
        Future<String> future2 = es.submit(newThreadByCallable);
        String s1 = future1.get();
        String s2 = future2.get();
        System.out.println(s1+s2);
        es.shutdown();

    }
}
