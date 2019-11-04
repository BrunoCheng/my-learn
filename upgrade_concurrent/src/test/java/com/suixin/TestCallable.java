package com.suixin;

import com.suixin.concurrent.L1Java里面的线程.L3线程间共享.L2Volatile关键字.Singleton;

import java.util.concurrent.*;

/**
 * @Description 测试Callable接口获取返回值
 * @Date 2019/7/23
 * @Created by acheng
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton> callable= new Callable<Singleton>() {
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
        System.out.println(s1 == s2);//true
        System.out.println(s1);
        System.out.println(s2);//地址值相同
        es.shutdown();//关闭线程服务
    }
}
