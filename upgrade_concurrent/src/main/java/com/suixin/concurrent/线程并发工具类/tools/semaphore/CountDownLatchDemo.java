package com.suixin.concurrent.线程并发工具类.tools.semaphore;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 倒计时器示例:火箭发射,摘自简书:https://www.jianshu.com/p/f17692e9114f
 * @Date 2019/7/17
 * @Created by acheng
 */
public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        // 模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //计数减一
            //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
            latch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池,里面10个线程
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++){
            //把任务提交上去
            exec.submit(demo);
        }

        // 等待检查,只有latch等于0的时候,主程序才会执行
        latch.await();

        // 发射火箭
        System.out.println("Fire!");
        // 关闭线程池
        exec.shutdown();
    }
}
