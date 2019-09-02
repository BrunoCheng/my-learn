package com.suixin.concurrent.Java里面的线程.启动和终止线程.如何安全的终止线程;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 抛出InterruptedException异常的时候，要注意中断标志位
 * @Date 2019/7/13
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
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " catch interrput flag is "
                            + isInterrupted() + " at "
                            + (formater.format(new Date())));
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName);
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
        endThread.interrupt();
        System.out.println(endThread.getState());

    }
}
