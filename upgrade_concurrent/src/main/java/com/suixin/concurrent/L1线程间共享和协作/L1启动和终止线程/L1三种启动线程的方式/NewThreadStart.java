package com.suixin.concurrent.L1线程间共享和协作.L1启动和终止线程.L1三种启动线程的方式;

import java.util.concurrent.*;

/**
 * @Description:开启线程的三种方法
 * @Auther:acheng
 */
public class NewThreadStart {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());//注意，这里打印的是主线程的名字：main
        //1、继承Thread类
        NewThreadByThread newThreadByThread = new NewThreadByThread();
        newThreadByThread.start();//执行重写的run方法，打印继承Thread开始的线程

        System.out.println(newThreadByThread.getName());//Thread-0

        //2、实现Runnable接口
        NewThreadByRunnable newThreadByRunnable = new NewThreadByRunnable();
        //创建Runnable实现类的实例，并以此实例作为Thread的target对象，即该Thread对象才是真正的线程对象
        new Thread(newThreadByRunnable).start();

        //3、实现Callable接口
        NewThreadByCallable newThreadByCallable = new NewThreadByCallable();
        //Callable与Runnable不同,Thread方法不知此Callable,为了将Callable放给Thread,将Callable包装成Runnable
        //Callable可呼叫的  Future未来，task任务
        FutureTask<String> stringFutureTask = new FutureTask<>(newThreadByCallable);
        //启动线程的方式
        new Thread(stringFutureTask).start();

        //启动的时候Thread线程是没有输出项的，需要自己获取Callbale的返回值
        System.out.println(stringFutureTask.get());

        //注意：FutureTask方式只能一个个的启动，对于多个线程情况比较麻烦
        //此时使用Executors来启动多个线程
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<String> future1 = es.submit(newThreadByCallable);
        Future<String> future2 = es.submit(newThreadByCallable);
        String s1 = future1.get();
        String s2 = future2.get();
        System.out.println(s1==s2);//true
        //使用Executors记得关闭线程
        es.shutdown();
    }
}
