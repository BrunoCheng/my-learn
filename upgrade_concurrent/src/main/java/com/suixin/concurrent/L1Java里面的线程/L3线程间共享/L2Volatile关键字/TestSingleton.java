package com.suixin.concurrent.L1Java里面的线程.L3线程间共享.L2Volatile关键字;

import java.util.concurrent.*;

/**
 * @Description 测试多线程
 * @Auther:acheng
 */
public class TestSingleton {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton>  callable= new Callable<Singleton>() {
            @Override
            public Singleton call() throws Exception {
                return Singleton.getSingleton();
            }
        };
        //Future<TestSingleton> f1 =  new Future<callable>();不能执行
        //需要放在线程池里面才可以
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton> f1 = es.submit(callable);
        Future<Singleton> f2 = es.submit(callable);
        Singleton s1 = f1.get();
        Singleton s2 = f2.get();
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
        es.shutdown();
    }
}
