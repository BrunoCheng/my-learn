package com.suixin.concurrent.线程并发工具类.tools;

import com.suixin.commons.utils.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 *类说明：演示CountDownLatch，有5个初始化的线程，6个扣除点，
 *扣除完毕以后，主线程和业务线程才能继续自己的工作
 */
public class UseCountDownLatch {
	
	static CountDownLatch latch = new CountDownLatch(6);

	//初始化线程(只有一步，有4个)
    private static class InitThread implements Runnable{

        @Override
        public void run() {
        	System.out.println("初始化线程Thread_"+Thread.currentThread().getId()
        			+" ready init work......");
        	latch.countDown();//初始化线程完成工作了，countDown方法只扣减一次；
            for(int i =0;i<2;i++) {

            	System.out.println("初始化线程Thread_"+Thread.currentThread().getId()
            			+" ........continue do its work");
            }
        }
    }
    
    //业务线程
    private static class BusiThread implements Runnable{

        @Override
        public void run() {
        	try {
        	    //业务线程等待
				latch.await();
				SleepTools.second(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            for(int i =0;i<3;i++) {
            	System.out.println("业务线程BusiThread_"+Thread.currentThread().getId()
            			+" do business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	//单独的初始化线程,初始化分为2步，需要扣减两次
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟场景消耗,其他的线程会抢夺
                SleepTools.sleepByMS(10);
                System.out.println("单独的初始化线程Thread_"+Thread.currentThread().getId()
            			+" ready init work step 1st......");
                latch.countDown();//每完成一步初始化工作，扣减一次
                System.out.println("begin step 2nd.......");
                SleepTools.sleepByMS(10);
                System.out.println("单独的初始化线程Thread_"+Thread.currentThread().getId()
            			+" ready init work step 2nd......");
                latch.countDown();//每完成一步初始化工作，扣减一次
            }
        }).start();
        //业务线程
        new Thread(new BusiThread()).start();
        //初始化线程
        for(int i=0;i<=3;i++){
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        //上面共计启动了5个线程,countDonw减了6次

        //主线程等待
        latch.await();
        System.out.println("主线程Main do ites work........");

        //代码逻辑
        //扣除点有6个,初始化线程减4个,单独初始化一条线程减了两次,业务线程await等待latch为0才开始执行,main也是如此
        //至于多个await哪个先执行由CPU调度
    }
}
