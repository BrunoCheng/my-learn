package com.suixin.concurrent.L1线程间共享和协作.L1启动和终止线程.L1三种启动线程的方式;

import com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L2Volatile关键字.Singleton;
import com.suixin.concurrent.L1线程间共享和协作.L3线程间共享.L2Volatile关键字.TestSingleton;

import java.util.concurrent.*;

/**
 * @Description 测试Callable接口获取返回值
 * @Date 2019/7/23
 * @Created by acheng
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton> callable = new Callable<Singleton>() {
            @Override
            public Singleton call() throws Exception {
                System.out.println("返回单例");
                return Singleton.getSingleton();
            }
        };


        //需要放在线程池里面才可以
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton> f1 = es.submit(callable);
        Future<Singleton> f2 = es.submit(callable);
        Singleton s1 = f1.get();
        Singleton s2 = f2.get();
        System.out.println(s1 == s2);//true
        System.out.println(s1);
        System.out.println(s2);//地址值相同
        es.shutdown();//关闭线程服务

        //
        FutureTask<Singleton> f3 = new FutureTask<>(callable);
        FutureTask<Singleton> f4 = new FutureTask<>(callable);
        Thread t1 = new Thread(f3);
        t1.start();
        Thread t2 = new Thread(f4);
        t2.start();
        Thread.sleep(100);
        System.out.println(f3.get());
        System.out.println(f4.get());
    }
}
