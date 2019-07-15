package com.suixin.concurrent.启动和终止线程.dbpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 测试自定义的一个简单的数据库连接池
 * @Date 2019/7/15
 * @Created by acheng
 */
public class BDPoolTest {
    // 定义一个数据连接池,初始连接池有10个连接
    static DBPool pool  = new DBPool(10);

    // 控制器:控制main线程将会等待所有Woker结束后才能继续执行
    //CountDownLatch线程工具类,相当于一个栅栏
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 定义线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        // 每个线程的操作次数
        int count = 20;
        // 计数器：统计可以拿到连接的线程
        AtomicInteger got = new AtomicInteger();
        // 计数器：统计超时之后没有拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot),
                    "worker_"+i);
            thread.start();
        }
        end.await();// main线程在此处等待
        System.out.println("总共尝试了: " + (threadCount * count));
        System.out.println("拿到连接的次数：  " + got);
        System.out.println("没能连接的次数： " + notGot);
        /**
         * worker_12等待超时!
         * worker_49等待超时!
         * .....
         * worker_41等待超时!
         * 总共尝试了: 1000
         * 拿到连接的次数：  873
         * 没能连接的次数： 127
         */
    }

    static class Worker implements Runnable {

        int           count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got,
                      AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            //每个线程赋予的操作数
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConn(1000);
                    if (connection != null) {
                        try {
                            //根据多态性运行的是SqlConnectImpl
                            //自定义创建连接消耗1ms
                            connection.createStatement();
                            //自定义提交连接消耗70ms
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            //增加1
                            got.incrementAndGet();
                        }
                    } else {
                        //没有获取到加1
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()
                                +"等待超时!");
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
