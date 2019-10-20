package com.suixin.concurrent.Java里面的线程.启动和终止线程.如何安全的终止线程;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 抛出InterruptedException异常的时候，要注意中断标志位
 * @Date 2016/7/13
 * @Created by acheng
 */
public class HasInterrputException {

    private static SimpleDateFormat formater
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    System.out.println("UseThread:" + formater.format(new Date()));
                    Thread.sleep(300);//控制阀,休眠时刻如果执行interrupt()方法就会抛出异常,被catch捕获
                } catch (InterruptedException e) {
                    System.out.println(threadName + " catch interrput flag is "
                            + isInterrupted() + " at "
                            + (formater.format(new Date())));
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println("当前一次循环try-catch结束 "+threadName);
            }
            System.out.println(threadName + " interrput flag is "
                    + isInterrupted()+"state is "+Thread.currentThread().getState());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("HasInterrputEx");
        endThread.start();
        System.out.println("Main:" + formater.format(new Date()));
        Thread.sleep(800);
        System.out.println("Main begin interrupt thread:" + formater.format(new Date()));
        endThread.interrupt();//当一个线程被sleep()方法阻塞,执行interrupt()会抛出InterruptedException
        System.out.println(endThread.getState());

    }
}
//Main:2019-10-20 15:34:26_252
//UseThread:2019-10-20 15:34:26_252
//当前一次循环try-catch结束 HasInterrputEx
//UseThread:2019-10-20 15:34:26_553
//当前一次循环try-catch结束 HasInterrputEx
//UseThread:2019-10-20 15:34:26_853
//Main begin interrupt thread:2019-10-20 15:34:27_054
//HasInterrputEx catch interrput flag is false at 2019-10-20 15:34:27_054
//TIMED_WAITING
//java.lang.InterruptedException: sleep interrupted
//	at java.lang.Thread.sleep(Native Method)
//	at com.suixin.concurrent.Java里面的线程.启动和终止线程.如何安全的终止线程.HasInterrputException$UseThread.run(HasInterrputException.java:28)
//当前一次循环try-catch结束 HasInterrputEx
//HasInterrputEx interrput flag is truestate is RUNNABLE
