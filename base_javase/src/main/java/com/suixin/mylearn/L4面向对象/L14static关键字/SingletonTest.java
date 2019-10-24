package com.suixin.mylearn.L4面向对象.L14static关键字;

import java.util.concurrent.*;

/**
 * @Description 单例模式测试
 * @Date 2019/10/22
 * @Created by acheng
 */
public class SingletonTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<SingletonDemo2> callable = new Callable<SingletonDemo2>() {
            @Override
            public SingletonDemo2 call() throws Exception {
                return SingletonDemo2.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<SingletonDemo2> s1 = es.submit(callable);
        Future<SingletonDemo2> s2 = es.submit(callable);
        Future<SingletonDemo2> s3 = es.submit(callable);
        SingletonDemo2 singleton1 = s1.get();
        SingletonDemo2 singleton2 = s2.get();
        SingletonDemo2 singleton3 = s3.get();
        System.out.println(singleton1==singleton2);//true
        System.out.println(singleton2==singleton3);//true
        es.shutdown();
    }
}
